package net.skhu.skhu_711;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Select extends AppCompatActivity {

    ArrayList<Facility_item> arrayList;
    FacilityAdapter facilityAdapter;

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

        //건물목록을 나타내주는 RecyclerView 구현 시작
        arrayList = new ArrayList<>();
        arrayList.add(new Facility_item("6201","aaa","132",R.drawable.ic_launcher_foreground));
        arrayList.add(new Facility_item("6202","aab","133",R.drawable.ic_launcher_foreground));
        arrayList.add(new Facility_item("6203","aac","134",R.drawable.ic_launcher_foreground));
        arrayList.add(new Facility_item("6204","aad","135",R.drawable.ic_launcher_foreground));
        arrayList.add(new Facility_item("6205","aae","136",R.drawable.ic_launcher_foreground));
        arrayList.add(new Facility_item("6206","aaf","137",R.drawable.ic_launcher_foreground));
        arrayList.add(new Facility_item("6207","aag","138",R.drawable.ic_launcher_foreground));


        facilityAdapter = new FacilityAdapter(getApplicationContext(),this, arrayList);
        RecyclerView recyclerView1 = (RecyclerView)findViewById(R.id.facilityView);
        recyclerView1.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView1.setAdapter(facilityAdapter);
        //건물 목록을 나타내주는 RecyclerView 구현 완료

    }
}
