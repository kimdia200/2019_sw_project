package net.skhu.skhu_711;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
