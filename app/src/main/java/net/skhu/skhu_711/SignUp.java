package net.skhu.skhu_711;

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

public class SignUp extends AppCompatActivity {

    static final String url = "https://dev.mobile.shouwn.com/";
    EditText nameBox;
    EditText idBox;
    EditText pwBox;
    EditText pw2Box;
    EditText emailBox;
    String name,id,pw,pw2,email;
    int id_check =0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        nameBox = (EditText)findViewById(R.id.input_Name);
        idBox = (EditText)findViewById(R.id.input_Id);
        pwBox = (EditText)findViewById(R.id.input_pw);
        pw2Box = (EditText)findViewById(R.id.input_pw2);
        emailBox = (EditText)findViewById(R.id.input_email);

    }
    static boolean isEmptyOrWhiteSpace(String s){
        if(s ==null) return true;
        return s.trim().length() ==0;
    }
    public void onClick(View view){

        int buttonId = view.getId();
        name = nameBox.getText().toString();
        id = idBox.getText().toString();
        pw = pwBox.getText().toString();
        pw2 = pw2Box.getText().toString();
        email = emailBox.getText().toString();
        if(buttonId == R.id.btn_1) {
            if(isEmptyOrWhiteSpace(name)){
                //이름박스가 빈공간이면
                nameBox.setError("이름을 입력해주세요");
            }
            else if(isEmptyOrWhiteSpace(id)){
                //id박스가 빈공간이면
                idBox.setError("아이디를 입력해주세요");
            }else if(id_check==0){
                //id중복확인 안했을경우
                idBox.setError("중복확인을 해주세요");
            }else if(isEmptyOrWhiteSpace(pw)){
                //비밀번호 입력안했을경우
                pwBox.setError("비밀번호를 입력해주세요");
            }else if(pw.equals(pw2)==false){
                //비밀번호가 일치하지 않는경우
                pw2Box.setError("비밀번호가 일치하지 않습니다");
            }else if(isEmptyOrWhiteSpace(email)){
                //이메일이 공백일경우
                emailBox.setError("이메일을 입력해주세요");
            }else{
                //모두 정상적이면
                //id,pw,pw2,email값 서버로 전송
                sendSignUp();

            }
        }else if(buttonId == R.id.btn_2){
            id_check++;
            Toast.makeText(this, "ID중복확인 완료", Toast.LENGTH_SHORT).show();
            //현재 중복확인 api미구현
        }


    }
    public void sendSignUp(){
        //레트로핏 객체생성
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build();

        //name,id,pw,email 정보를 담아서 보낼 SignUpRequest객체 생성
        SignUpRequest signUpRequest = new SignUpRequest(name,id,pw,email);
        SkhuService skhuService = retrofit.create(SkhuService.class);
        Call<SignUpResponse> call = skhuService.postSignUp(signUpRequest);
        call.enqueue(new Callback<SignUpResponse>() {
            //요청 성공시
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                SignUpResponse res = response.body();
                String msg = res.getMessage();
                //회원가입 성공시
                if(msg.equals("학생 생성 성공")) {
                    Toast.makeText(SignUp.this, "회원가입 완료", Toast.LENGTH_SHORT).show();
                    //finish(); 를 통해 액티비티 종료
                    finish();
                //비밀번호 강도가 약할시
                }else if(msg.equals("비밀번호 강도가 약합니다.")){
                    Toast.makeText(SignUp.this, msg, Toast.LENGTH_SHORT).show();
                    pwBox.setError("영문숫자 혼합 8자리 이상");
                //그외 회원가입실패시 서버 msg그대로 보여줌
                }else{
                    Toast.makeText(SignUp.this, msg, Toast.LENGTH_SHORT).show();
                }

            }
            //요청 실패시
            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                //에러로그 생성
                t.printStackTrace();
            }
        });
    }
}
