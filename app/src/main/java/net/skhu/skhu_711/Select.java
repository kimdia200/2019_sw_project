package net.skhu.skhu_711;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

public class Select extends AppCompatActivity {

    ArrayList<ClassRoom_item> arrayList;
    ClassRoomAdapter classRoomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        TextView t1 = (TextView)findViewById(R.id.ggg);


        String s1 = getIntent().getExtras().getString("bCode");
        String s2 = getIntent().getExtras().getString("bName");

        t1.setText(s1+". "+s2);

        //건물목록을 나타내주는 RecyclerView 구현 시작
        arrayList = new ArrayList<>();
        arrayList.add(new ClassRoom_item("6201",30,"NULL","BIG",R.drawable.class3));
        arrayList.add(new ClassRoom_item("6202",30,"NULL","BIG",R.drawable.class3));
        arrayList.add(new ClassRoom_item("6203",30,"NULL","BIG",R.drawable.class3));
        arrayList.add(new ClassRoom_item("6204",30,"NULL","BIG",R.drawable.class3));
        arrayList.add(new ClassRoom_item("6205",30,"NULL","BIG",R.drawable.class3));
        arrayList.add(new ClassRoom_item("6206",30,"NULL","BIG",R.drawable.class3));
        arrayList.add(new ClassRoom_item("6207",30,"NULL","BIG",R.drawable.class3));
        arrayList.add(new ClassRoom_item("6208",30,"NULL","BIG",R.drawable.class3));


        classRoomAdapter = new ClassRoomAdapter(getApplicationContext(),this, arrayList);
        RecyclerView recyclerView1 = (RecyclerView)findViewById(R.id.classRoomView);
        recyclerView1.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView1.setAdapter(classRoomAdapter);
        //건물 목록을 나타내주는 RecyclerView 구현 완료

    }
}
