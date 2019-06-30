package net.skhu.skhu_711;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class BookingList extends AppCompatActivity {
    String fCode;
    String bCode;
    String bName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_list);
        TextView t1 = (TextView)findViewById(R.id.test);
        Button btn1 = (Button)findViewById(R.id.btn_booking);

        fCode = getIntent().getStringExtra("detailType");
        bCode = getIntent().getStringExtra("bCode");
        bName = getIntent().getStringExtra("bName");
        t1.setText(bCode+". "+bName+"  "+fCode);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),RequestPage.class);
                intent.putExtra("detailType",fCode);
                intent.putExtra("bCode",bCode);
                intent.putExtra("bName",bName);
                startActivity(intent);
                finish();
            }
        };
        btn1.setOnClickListener(listener);
    }
}
