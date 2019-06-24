package net.skhu.skhu_711;

import android.app.Activity;
import android.content.Context;
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

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.Gson;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main extends AppCompatActivity {

    ArrayList<Building_item> arrayList;
    ArrayList<MyList_item> arrayList2;
    BuildingAdapter buildingAdapter;
    MyListAdapter myListAdapter;
    String stdId;
    static final String url = "https://dev.mobile.shouwn.com/";
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        String stdId = getIntent().getStringExtra("stdId");
//        UserStatus.setText(stdId+"님 환영합니다");
        //intent로 넘겨받은 값을 이용해서 학번+"님 환영합니다"표시 함없애버렸음


        getUserInfo();


        //건물목록을 나타내주는 RecyclerView 구현 시작
        arrayList = new ArrayList<>();
        arrayList.add(new Building_item("승연관","1",R.drawable.ic_launcher_foreground));
        arrayList.add(new Building_item("일만관","2",R.drawable.ic_launcher_foreground));
        arrayList.add(new Building_item("월당관","3",R.drawable.ic_launcher_foreground));
        arrayList.add(new Building_item("이천환관","6",R.drawable.ic_launcher_foreground));
        arrayList.add(new Building_item("새천년관","7",R.drawable.ic_launcher_foreground));
        arrayList.add(new Building_item("성미가엘성당","9",R.drawable.ic_launcher_foreground));
        arrayList.add(new Building_item("미가엘관","11",R.drawable.ic_launcher_foreground));

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
        //retrofit2라이브러리로 http통신해서 나의 신청현황 가져옴
        getRentalList();
        arrayList2.add(new MyList_item(1,new RentalDate(10,12,dd),"승인대기중","6202",true));
        arrayList2.add(new MyList_item(2,new RentalDate(13,14,dd),"승인","6206",false));
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
    public void getRentalList(){
        //라이브러리로 쓴거
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(this.getApplicationContext()));
        final OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build();

//        OkHttpClient client = new OkHttpClient();
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//
//        builder.addInterceptor(new AddCookiesInterceptor(Main.this)); // VERY VERY IMPORTANT
//        builder.addInterceptor(new RecievedCookiesInterceptor(Main.this)); // VERY VERY IMPORTANT
//        client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .client(client)
                .build();

        token = getIntent().getStringExtra("token");
        SkhuService service = retrofit.create(SkhuService.class);
        Call<RentalListResponse> call = service.getRentalList(token);
        call.enqueue(new Callback<RentalListResponse>() {
            @Override
            public void onResponse(Call<RentalListResponse> call, Response<RentalListResponse> response) {
                RentalListResponse res = response.body();
                arrayList2 = (ArrayList<MyList_item>)res.getData();
                Toast.makeText(Main.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                //현재 500값만 넘어와 코드를 수정해야함 현재 에러상태
            }

            @Override
            public void onFailure(Call<RentalListResponse> call, Throwable t) {
                Toast.makeText(Main.this, "실패야?", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
    public void getUserInfo(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build();

        token = getIntent().getStringExtra("token");
        SkhuService service = retrofit.create(SkhuService.class);
        Call<UserInfoResponse> call = service.getUserInfo(token);
        call.enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                UserInfoResponse res = (UserInfoResponse)response.body();
                stdId = res.getData().getName();
                TextView UserStatus = (TextView)findViewById(R.id.textView_id);
                UserStatus.setText(stdId+"님 환영합니다");
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {

            }
        });


    }
}
