package net.skhu.skhu_711;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SkhuService {

    //Header부분 넣어줌
    @Headers({"Content-Type:application/json"})

    //Post요청 메소드 postLogin생성(LoginActivity에서 사용함)
    @POST("/student/auth/login")
    //url
    Call<LoginResponse> postLogin(
            //요청에 대한 응답을 LoginResponse객체로 반환받고 메소드 이름 postLogin
            @Body LoginRequest loginrequest
            //Body값에 LoginRequest객체를 넣어서 요청
    );

}
