package net.skhu.skhu_711;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Certification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);

        //if 인증 토큰이 있으면 바로 다음 화면으로 넘어가고
        //인증 토큰이 없으면 밑에 과정을 통해 인증토큰 생성
        //sharedpreferences 사용
        

        Button btn1 = (Button)findViewById(R.id.btn_Certification);
        View.OnClickListener listener = new View.OnClickListener() {
            //인증버튼에 달아줄 리스너 생성
            @Override
            public void onClick(View view) {
                //인증받기 api이용
                //인증 성공시 토큰생성
                //인증 실패시 오류 알림
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
            }
        };
        btn1.setOnClickListener(listener);
    }
}
