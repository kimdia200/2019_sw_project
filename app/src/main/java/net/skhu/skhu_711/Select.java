package net.skhu.skhu_711;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Select extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        TextView t1 = (TextView)findViewById(R.id.ggg);
        TextView t2 = (TextView)findViewById(R.id.hhh);

        String s = getIntent().getExtras().getString("bName");
        String s2 = getIntent().getExtras().getString("bCode");

        t1.setText(s);
        t2.setText(s2);

    }
}
