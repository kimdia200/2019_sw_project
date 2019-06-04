package net.skhu.skhu_711;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Certification extends AppCompatActivity {


    String stdId, stdPw;
    EditText editText1,editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage(R.string.certification_msg);
//        builder.setNeutralButton("닫기",null);
//        AlertDialog dialog =builder.create();
//        dialog.show();


        SharedPreferences auto2 = getSharedPreferences("auto2", Activity.MODE_PRIVATE);
        stdId = auto2.getString("inputStdId",null);
        stdPw = auto2.getString("inputStdPw",null);
        editText1 = (EditText)findViewById(R.id.input_Std_Id);
        editText2 = (EditText)findViewById(R.id.input_std_pw);


        if(stdId != null && stdPw!=null){
            if(stdId.equals("b")&&stdPw.equals("b")){
                //자동로그인조건 만족시 다음페이지로 넘어감
                Toast.makeText(this, "자동 인증합니다", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Main.class);
                intent.putExtra("stdId",stdId);
                startActivity(intent);
                finish();
            }
        }
        Toast.makeText(this, stdId + stdPw, Toast.LENGTH_SHORT).show();
        

        Button btn1 = (Button)findViewById(R.id.btn_Certification);
        View.OnClickListener listener = new View.OnClickListener() {
            //인증버튼에 달아줄 리스너 생성
            @Override
            public void onClick(View view) {
                if(editText1.getText().toString().equals("b")&&editText2.getText().toString().equals("b")){
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
                    startActivity(intent);
                    finish();
                }
                else{
                    //로그인조건 불만족시
                    Toast.makeText(Certification.this, "학사정보 불일치", Toast.LENGTH_SHORT).show();
                }
            }
        };
        btn1.setOnClickListener(listener);
        Button btn2 = (Button)findViewById(R.id.btn_back);
        View.OnClickListener listener2 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Certification.this, Login.class);
                startActivity(intent);
                SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = auto.edit();
                //editor.clear()는 auto에 들어있는 모든 정보를 기기에서 지웁니다.
                editor.clear();
                editor.commit();
                finish();
            }
        };
        btn2.setOnClickListener(listener2);
    }
}
