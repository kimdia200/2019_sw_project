package net.skhu.skhu_711;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    EditText idBox;
    EditText pwBox;
    EditText pw2Box;
    EditText emailBox;
    Button btn1;
    Button btn2;
    int id_check =0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);




        idBox = (EditText)findViewById(R.id.input_Id);
        pwBox = (EditText)findViewById(R.id.input_pw);
        pw2Box = (EditText)findViewById(R.id.input_pw2);
        emailBox = (EditText)findViewById(R.id.input_email);


        String id = idBox.getText().toString();
        String pw = pwBox.getText().toString();
        String pw2 = pw2Box.getText().toString();
        String email = emailBox.getText().toString();






    }
    static boolean isEmptyOrWhiteSpace(String s){
        if(s ==null) return true;
        return s.trim().length() ==0;
    }
    public void onClick(View view){

        int buttonId = view.getId();
        String id = idBox.getText().toString();
        String pw = pwBox.getText().toString();
        String pw2 = pw2Box.getText().toString();
        if(buttonId == R.id.btn_1) {
            if(isEmptyOrWhiteSpace(id)){
                //id박스가 빈공간이면
                idBox.setError("아이디를 입력해주세요");
            }else if(id_check==0){
                //id중복확인 안했을경우
                idBox.setError("중복확인을 해주세요");
            }
            if(isEmptyOrWhiteSpace(pw)){
                //비밀번호 입력안했을경우
                pwBox.setError("비밀번호를 입력해주세요");
            }else if(1==1){
                //비밀번호가 양식에 맞지않는경우
                pwBox.setError("비밀번호 양식을 맞춰주세요");
            }else if(pw.equals(pw2)==false){
                //비밀번호가 일치하지 않는경우
                pw2Box.setError("비밀번호가 일치하지 않습니다");
            }else{
                //모두 정상적이면
                //id,pw,pw2,email값 서버로 전송
                //finish(); 를 통해 액티비티 종료

            }
        }else if(buttonId == R.id.btn_2){
            //만약 중복이지 않으면 id_check=1;
            //중복이면 에러 다이얼로그 표시
        }


    }
}
