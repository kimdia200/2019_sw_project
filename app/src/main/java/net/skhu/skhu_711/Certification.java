package net.skhu.skhu_711;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.CookieManager;
import java.net.CookiePolicy;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Certification extends AppCompatActivity {

    final static String url = "https://dev.mobile.shouwn.com/";
    String stdId, stdPw;
    EditText editText1,editText2;
    String token,refToken;
    String s1,s2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);

        //학사정보시스템 로그인정보 안내 Alert Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.certification_msg);
        builder.setNeutralButton("닫기",null);
        AlertDialog dialog =builder.create();
        dialog.show();
        //추후 Alert말고 뷰로 표시할지 고민중

        //sharedPreferences 객체(자동로그인용)
        SharedPreferences auto2 = getSharedPreferences("auto2", Activity.MODE_PRIVATE);
        stdId = auto2.getString("inputStdId",null);
        stdPw = auto2.getString("inputStdPw",null);
        editText1 = (EditText)findViewById(R.id.input_Std_Id);
        editText2 = (EditText)findViewById(R.id.input_std_pw);

        //intent로 Login액티비티에서 넘겨준 토큰값,리프레쉬 토큰값 받아옴
        token = "Bearer "+getIntent().getStringExtra("token");
        refToken = getIntent().getStringExtra("refToken");

        //기존에 sharedPreferences값이 존재한다면 자동로그인 실행
        if(stdId != null && stdPw!=null){
                //자동로그인조건 만족시 다음페이지로 넘어감
            editText1.setText(stdId);
            editText2.setText(stdPw);
            forestLogin();
        }
        //인증 버튼 눌렀을경우
        Button btn1 = (Button)findViewById(R.id.btn_Certification);
        View.OnClickListener listener = new View.OnClickListener() {
            //인증버튼에 달아줄 리스너 생성
            @Override
            public void onClick(View view) {
                forestLogin();
            }
        };
        //btn1에 리스너 달아줌
        btn1.setOnClickListener(listener);

        //Login액티비티로 돌아가는 back버튼 눌렀을경우
        Button btn2 = (Button)findViewById(R.id.btn_back);
        View.OnClickListener listener2 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Certification.this, Login.class);
                startActivity(intent);
                //앱 자동로그인 정보 삭제 구현시작
                SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = auto.edit();
                //editor.clear()는 auto에 들어있는 모든 정보를 기기에서 지웁니다.
                editor.clear();
                //editor.commit()는 auto에 대한 수정값을 최종적으로 저장해줍니다
                editor.commit();
                //앱 자동로그인 정보 삭제 구현 완료
                finish();
                //login Activity로 이동후 현재(Certification Activity종료)
            }
        };
        //btn2에 리스너 달아줌
        btn2.setOnClickListener(listener2);
    }


    //forestLogin함수
    public void forestLogin(){

        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addNetworkInterceptor(new AddCookiesInterceptor(Certification.this)); // VERY VERY IMPORTANT
        builder.addInterceptor(new RecievedCookiesInterceptor(Certification.this)); // VERY VERY IMPORTANT
        client = builder.build();

        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .client(client)
                .build();
        s1 = editText1.getText().toString();
        s2 = editText2.getText().toString();
        ForestLoginRequest req = new ForestLoginRequest(s1,s2);
        SkhuService service = retrofit.create(SkhuService.class);


        Call<ForestLoginResponse> call = service.postForestLogin(token,req);
        call.enqueue(new Callback<ForestLoginResponse>() {
            @Override
            public void onResponse(Call<ForestLoginResponse> call, Response<ForestLoginResponse> response) {
                String msg = "";
                ForestLoginResponse res = (ForestLoginResponse)response.body();
                msg=res.getMessage();
                if(msg.equals("로그인 성공")){
                    //로그인조건 만족시 sharedpreferences 생성
                    SharedPreferences auto2 = getSharedPreferences("auto2",Activity.MODE_PRIVATE);
                    SharedPreferences.Editor autoLogin2 = auto2.edit();
                    autoLogin2.putString("inputStdId",editText1.getText().toString());
                    autoLogin2.putString("inputStdPw",editText2.getText().toString());
                    autoLogin2.commit();
                    Toast.makeText(Certification.this, "학사 인증완료", Toast.LENGTH_SHORT).show();


                    //로그인조건 만족시 다음페이지로 넘어감
                    Intent intent = new Intent(getApplicationContext(), Main.class);
                    intent.putExtra("stdId",editText1.getText().toString());
                    intent.putExtra("token",token);
                    intent.putExtra("refToken",refToken);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(Certification.this, msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ForestLoginResponse> call, Throwable t) {
                Log.e("onFailure",t.toString());
                t.printStackTrace();

            }
        });
    }
}
