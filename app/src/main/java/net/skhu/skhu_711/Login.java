package net.skhu.skhu_711;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText editText1;
    EditText editText2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText1 = (EditText)findViewById(R.id.input_Id);
        editText2 = (EditText)findViewById(R.id.input_pw);
    }

    public void onclick(View view){
        int buttonId = view.getId();
        
        if(buttonId == R.id.btn_1) {
            //로그인버튼이 눌리면 서버에서 아이디비밀번호 확인함
            if(editText1.getText().toString().equals("a")){
                //로그인조건 만족시 다음페이지로 넘어감
                Intent intent = new Intent(this, Certification.class);
                startActivity(intent);
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
