package net.skhu.skhu_711;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);


        TextView UserStatus = (TextView)findViewById(R.id.textView_id);
        String stdId = getIntent().getStringExtra("stdId");
        UserStatus.setText(stdId+"님 환영합니다");


        Button btn = (Button)findViewById(R.id.btn_click);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //예약화면으로 인텐트
                Intent intent = new Intent(getApplicationContext(), Select.class);
                startActivity(intent);
            }
        };
        btn.setOnClickListener(listener);
        Button btn2 = (Button)findViewById(R.id.btn_logout);
        View.OnClickListener listener2 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, Login.class);
                startActivity(intent);
                SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = auto.edit();
                //editor.clear()는 auto에 들어있는 모든 정보를 기기에서 지웁니다.
                editor.clear();
                editor.commit();
                SharedPreferences auto2 = getSharedPreferences("auto2", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor2 = auto2.edit();
                //editor2.clear()는 auto2에 들어있는 모든 정보를 기기에서 지웁니다.
                editor2.clear();
                editor2.commit();
                Toast.makeText(Main.this, "로그아웃", Toast.LENGTH_SHORT).show();
                finish();
            }
        };
        btn2.setOnClickListener(listener2);
    }

}
