package net.skhu.skhu_711;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {

    ArrayList<Building_item> arrayList;
    ArrayList<MyList_item> arrayList2;
    BuildingAdapter buildingAdapter;
    MyListAdapter myListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView UserStatus = (TextView)findViewById(R.id.textView_id);
//        String stdId = getIntent().getStringExtra("stdId");
//        UserStatus.setText(stdId+"님 환영합니다");
        //intent로 넘겨받은 값을 이용해서 학번+"님 환영합니다"표시 함없애버렸음


        //건물목록을 나타내주는 RecyclerView 구현 시작
        arrayList = new ArrayList<>();
        arrayList.add(new Building_item("승연관","A",R.drawable.ic_launcher_foreground));
        arrayList.add(new Building_item("일만관","B",R.drawable.ic_launcher_foreground));
        arrayList.add(new Building_item("월당관","C",R.drawable.ic_launcher_foreground));
        arrayList.add(new Building_item("이천환관","D",R.drawable.ic_launcher_foreground));
        arrayList.add(new Building_item("새천년관","E",R.drawable.ic_launcher_foreground));
        arrayList.add(new Building_item("성미가엘성당","F",R.drawable.ic_launcher_foreground));
        arrayList.add(new Building_item("미가엘관","G",R.drawable.ic_launcher_foreground));

        buildingAdapter = new BuildingAdapter(this, arrayList);
        RecyclerView recyclerView1 = (RecyclerView)findViewById(R.id.BuildingView);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView1.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView1.setAdapter(buildingAdapter);
        //건물 목록을 나타내주는 RecyclerView 구현 완료

        //나의 예약현황을 나타내주는 RecyclerView 구현 시작
        LocalDate dd = LocalDate.now();
        arrayList2 = new ArrayList<>();
        arrayList2.add(new MyList_item(1,new RentalDate(10,12,dd),"승인대기중","6202",true));
        arrayList2.add(new MyList_item(2,new RentalDate(10,12,dd),"승인","6206",false));
        myListAdapter = new MyListAdapter(this,arrayList2);
        RecyclerView recyclerView2 = (RecyclerView)findViewById(R.id.BookingView);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView2.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(myListAdapter);
        //나의 예약현황을 나타내주는 RecyclerView 구현 종료

        //로그아웃 버튼 구현 시작
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
        //로그아웃 버튼 구현 완료
    }

}
