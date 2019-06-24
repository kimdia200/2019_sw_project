package net.skhu.skhu_711;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RequestPage extends AppCompatActivity {

    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_page);


        btn1 =(Button)findViewById(R.id.btn_request);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RequestPage.this, "대여신청완료", Toast.LENGTH_SHORT).show();
                finish();
            }
        };
        btn1.setOnClickListener(listener);
    }
}
