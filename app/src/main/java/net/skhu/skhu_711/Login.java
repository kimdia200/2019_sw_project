package net.skhu.skhu_711;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    final static String url = "https://dev.mobile.shouwn.com/";
    EditText editText1;
    EditText editText2;
    String loginId, loginPw;
    Button btn1, btn2;
    String msg;
    String token;
    String refToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText1 = (EditText)findViewById(R.id.input_Id);
        editText2 = (EditText)findViewById(R.id.input_pw);
        btn1 = (Button)findViewById(R.id.btn_1);
        btn2 = (Button)findViewById(R.id.btn_2);
        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        loginId = auto.getString("inputId",null);
        loginPw = auto.getString("inputPw",null);


        if(loginId != null && loginPw!=null){
            //자동로그인조건 만족시 다음페이지로 넘어감
            editText1.setText(loginId);
            editText2.setText(loginPw);
            check_logIn();
        }
    }

    public void onclick(View view){
        int buttonId = view.getId();

        //로그인버튼이 눌리면 서버에서 아이디비밀번호 확인함
        if(buttonId == R.id.btn_1) {
            //유효한 로그인인지 검사
            check_logIn();
        }
        //회원가입 버튼이 눌리면
        else if(buttonId ==R.id.btn_2){
            //회원가입 버튼이 눌리면
            Toast.makeText(this, "회원가입 창으로 전환합니다", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
        }
    };

    public void check_logIn(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build();
        SkhuService service = retrofit.create(SkhuService.class);
        LoginRequest req = new LoginRequest(editText1.getText().toString(),editText2.getText().toString());
        Call<LoginResponse> call = service.postLogin(req);
        call.enqueue(new Callback<LoginResponse>() {

            @Override
            //서버통신이 성공적이면
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse res = (LoginResponse)response.body();
                msg = res.getMessage();
                //로그인이 성공적이면
                if(msg.equals("로그인 성공")){
                    //로그인조건 만족시 sharedpreferences 생성
                    SharedPreferences auto = getSharedPreferences("auto",Activity.MODE_PRIVATE);
                    SharedPreferences.Editor autoLogin = auto.edit();
                    autoLogin.putString("inputId",editText1.getText().toString());
                    autoLogin.putString("inputPw",editText2.getText().toString());
                    autoLogin.commit();
                    //토큰값 저장
                    token = res.getData().getToken();
                    refToken = res.getData().getRefreshToken();
                    Toast.makeText(Login.this, "로그인 완료", Toast.LENGTH_SHORT).show();


                    //로그인조건 만족시 다음페이지로 넘어감
                    Intent intent = new Intent(getApplicationContext(),Certification.class);
                    intent.putExtra("token",token);
                    intent.putExtra("refToken",refToken);
                    startActivity(intent);
                    finish();
                }else{
                    //로그인 실패시 로그인실패 메시지 보여줌
                    //아이디 틀릴때
                    if(res.getCode()==404) {
                        Toast.makeText(Login.this, msg, Toast.LENGTH_LONG).show();
                    }
                    //비밀번호 틀릴때
                    else{
                        Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Login.this, "서버 연결 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
