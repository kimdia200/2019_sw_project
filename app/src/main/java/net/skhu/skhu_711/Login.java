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

public class Login extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    String loginId, loginPw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText1 = (EditText)findViewById(R.id.input_Id);
        editText2 = (EditText)findViewById(R.id.input_pw);
        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        loginId = auto.getString("inputId",null);
        loginPw = auto.getString("inputPw",null);

        if(loginId != null && loginPw!=null){
            if(loginId.equals("a")&&loginPw.equals("a")){
                //자동로그인조건 만족시 다음페이지로 넘어감
                Toast.makeText(this, "자동로그인합니다", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Certification.class);
                startActivity(intent);
                finish();
            }
        }
        Toast.makeText(this, loginId + loginPw, Toast.LENGTH_SHORT).show();
    }

    public void onclick(View view){
        int buttonId = view.getId();
        
        if(buttonId == R.id.btn_1) {
            //로그인버튼이 눌리면 서버에서 아이디비밀번호 확인함
            if(editText1.getText().toString().equals("a")&&editText2.getText().toString().equals("a")){
                //로그인조건 만족시 sharedpreferences 생성
                SharedPreferences auto = getSharedPreferences("auto",Activity.MODE_PRIVATE);
                SharedPreferences.Editor autoLogin = auto.edit();
                autoLogin.putString("inputId",editText1.getText().toString());
                autoLogin.putString("inputPw",editText2.getText().toString());
                autoLogin.commit();
                Toast.makeText(this, "로그인완료", Toast.LENGTH_SHORT).show();


                //로그인조건 만족시 다음페이지로 넘어감
                Intent intent = new Intent(this, Certification.class);
                startActivity(intent);
                finish();
            }
            else{
                //로그인조건 불만족시
                Toast.makeText(this, "아이디와 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            }
        }
        else if(buttonId ==R.id.btn_2){
            //회원가입 버튼이 눌리면
            Toast.makeText(this, "회원가입 창으로 전환합니다", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
        }
    };
}
