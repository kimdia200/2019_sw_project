package net.skhu.skhu_711;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Select extends AppCompatActivity {

    ArrayList<ClassRoom_item> arrayList;
    ClassRoomAdapter classRoomAdapter;
    static final String url = "https://dev.mobile.shouwn.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        TextView t1 = (TextView)findViewById(R.id.ggg);
        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);

        String s1 = getIntent().getExtras().getString("bCode");
        String s2 = getIntent().getExtras().getString("bName");
        String token = getSharedPreferences("auto",MODE_PRIVATE).getString("token",null);
        t1.setText(s1+". "+s2);

        int buildingNumber = Integer.parseInt(s1);

        //쿠키 읽기, 저장을 목적으로한 OKHTTP 객체 생성
        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addNetworkInterceptor(new AddCookiesInterceptor(Select.this)); // VERY VERY IMPORTANT
        builder.addInterceptor(new RecievedCookiesInterceptor(Select.this)); // VERY VERY IMPORTANT
        client = builder.build();


        //HTTP통신을 위한 RETROFIT객체 생성 및 OKHTTP 객체 추가
        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .client(client)
                .build();
        SkhuService service = retrofit.create(SkhuService.class);
        Call<ClassRoomResponse> call = service.getClassRoomList(token,buildingNumber);
        call.enqueue(new Callback<ClassRoomResponse>() {
            @Override
            public void onResponse(Call<ClassRoomResponse> call, Response<ClassRoomResponse> response) {

                ClassRoomResponse res = response.body();
                Toast.makeText(Select.this, res.getMessage(), Toast.LENGTH_SHORT).show();

                ArrayList<ClassRoom_item> arrayList = new ArrayList<>();
                ClassRoomAdapter classRoomAdapter;
                RecyclerView recyclerView;
                for(ClassRoom_item x : res.getData()){
                    //추후 이미지 조건걸어서 종류별로 설정 해줘야함!
                    x.setImg_src(R.drawable.class3);
                    arrayList.add(x);
                }
                //나의 예약현황을 나타내주는 RecyclerView 구현 시작
                classRoomAdapter = new ClassRoomAdapter(getApplicationContext(),Select.this,arrayList);
                RecyclerView recyclerView1 = (RecyclerView)findViewById(R.id.classRoomView);
                recyclerView1.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                recyclerView1.setItemAnimator(new DefaultItemAnimator());
                recyclerView1.setAdapter(classRoomAdapter);
                //나의 예약현황을 나타내주는 RecyclerView 구현 종료


                //통신 성공시 리스폰값 받아올수있는지 유무에 대한 로그캣
                if (response.isSuccessful())
                    Log.e("Success", new Gson().toJson(response.body()));
                else
                    Log.e("unSuccess", new Gson().toJson(response.errorBody()));
            }

            @Override
            public void onFailure(Call<ClassRoomResponse> call, Throwable t) {
                Log.e("onFailure",t.toString());
            }
        });
    }
}
