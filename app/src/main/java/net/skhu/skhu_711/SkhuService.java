package net.skhu.skhu_711;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SkhuService {

    //Header부분 넣어줌
    @Headers({"Content-Type:application/json"})

    //Post요청 메소드(LoginActivity에서 사용함)
    @POST("/student/auth/login")
    //로그인url
    Call<LoginResponse> postLogin(
            //요청에 대한 응답을 LoginResponse객체로 반환받고 메소드 이름 postLogin
            @Body LoginRequest loginrequest
            //Body값에 LoginRequest객체를 넣어서 요청
    );

    //Post요청 메소드(SignUp Activity에서 사용함)
    @POST("/student/auth/signUp")
    //회원가입url
    Call<SignUpResponse> postSignUp(
            //요청에 대한 응답을 SignUpResponse객체로 반환받고 메소드 이름을 postSignUp
            @Body SignUpRequest signUpRequest
            //Body값에 SignUpRequest객체를 넣어서 요청
    );

//    @FormUrlEncoded
//    @POST("/user/login")
//    Call<ForestLoginResponse> postForestLogin(@Header("Authorization") String token),
//            @Field("ssss") String sssss,@Field("sssssss") String ddddddd;
//    );

    //Post요청 메소드(Certification Activity에서 사용함)
    @POST("/user/login")
    //SKHU forest 로그인
    Call<ForestLoginResponse> postForestLogin(
            //요청에 대한 응답을 ForestLoginResponse객체로 반환받고 메소드 이름을 postForestLogin
            @Header("Authorization") String token,
            @Body ForestLoginRequest forestLoginRequest
    );

}
