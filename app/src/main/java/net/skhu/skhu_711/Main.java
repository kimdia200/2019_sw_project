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
import android.widget.Toast;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);


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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.logOut){
            Intent intent = new Intent(Main.this, Login.class);
            startActivity(intent);
            SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = auto.edit();
            //editor.clear()는 auto에 들어있는 모든 정보를 기기에서 지웁니다.
            editor.clear();
            editor.commit();
            SharedPreferences auto2 = getSharedPreferences("auto2", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor2 = auto.edit();
            //editor2.clear()는 auto2에 들어있는 모든 정보를 기기에서 지웁니다.
            editor2.clear();
            editor2.commit();
            Toast.makeText(Main.this, "로그아웃.", Toast.LENGTH_SHORT).show();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
