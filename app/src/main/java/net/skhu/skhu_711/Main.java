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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
    static final String url = "http://dev.mobile.shouwn.com/";
    String token;
    RecyclerView recyclerView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        String stdId = getIntent().getStringExtra("stdId");
//        UserStatus.setText(stdId+"님 환영합니다");
        //intent로 넘겨받은 값을 이용해서 학번+"님 환영합니다"표시 함없애버렸음

//         유저정보 불러오는 함수 수정필요(로그인방법 변경)
//        getUserInfo();


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



        //retrofit2라이브러리로 http통신해서 나의 신청현황 가져옴
        getRentalList();

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

        //쿠키 읽기, 저장을 목적으로한 OKHTTP 객체 생성
        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addNetworkInterceptor(new AddCookiesInterceptor(Main.this)); // VERY VERY IMPORTANT
        builder.addInterceptor(new RecievedCookiesInterceptor(Main.this)); // VERY VERY IMPORTANT
        client = builder.build();

        //HTTP통신을 위한 RETROFIT객체 생성 및 OKHTTP 객체 추가
        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .client(client)
                .build();
        SkhuService service = retrofit.create(SkhuService.class);
//        token = getIntent().getStringExtra("token"); 토큰 삭제처리

        Call<RentalListResponse> call = service.getRentalList();
        call.enqueue(new Callback<RentalListResponse>() {
            @Override
            public void onResponse(Call<RentalListResponse> call, Response<RentalListResponse> response) {
                RentalListResponse res = response.body();
                arrayList2 = new ArrayList<>();
                for(MyList_item x : res.getData()){
                    arrayList2.add(x);
                }

                //나의 예약현황을 나타내주는 RecyclerView 구현 시작
                myListAdapter = new MyListAdapter(getApplicationContext(),arrayList2);
                recyclerView2 = (RecyclerView)findViewById(R.id.BookingView);
                recyclerView2.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
                recyclerView2.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL));
                recyclerView2.setItemAnimator(new DefaultItemAnimator());
                recyclerView2.setAdapter(myListAdapter);
                //나의 예약현황을 나타내주는 RecyclerView 구현 종료
                Toast.makeText(Main.this, res.getMessage(), Toast.LENGTH_SHORT).show();

                //통신 성공시 리스폰값 받아올수있는지 유무에 대한 로그캣
                if (response.isSuccessful())
                    Log.e("Success", new Gson().toJson(response.body()));
                else
                    Log.e("unSuccess", new Gson().toJson(response.errorBody()));

            }

            @Override
            public void onFailure(Call<RentalListResponse> call, Throwable t) {
                Log.e("onFailure",t.toString());
                t.printStackTrace();
            }
        });
    }



    //유저이름 불러오는함수 수정이 필요함
//    public void getUserInfo(){
//        Retrofit retrofit = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(url)
//                .build();
//
////        token = getIntent().getStringExtra("token");
//        //토큰 삭제처리
//        SkhuService service = retrofit.create(SkhuService.class);
//        Call<UserInfoResponse> call = service.getUserInfo();
//        call.enqueue(new Callback<UserInfoResponse>() {
//            @Override
//            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
//                UserInfoResponse res = (UserInfoResponse)response.body();
//                stdId = res.getData().getName();
//                TextView UserStatus = (TextView)findViewById(R.id.textView_id);
//                UserStatus.setText(stdId+"님 환영합니다");
//            }
//
//            @Override
//            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
//
//            }
//        });
//
//
//    }
}
