package net.skhu.skhu_711;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

    //Post요청 메소드(Certification Activity에서 사용함)
    @POST("/user/login")
    //SKHU forest 로그인
    Call<ForestLoginResponse> postForestLogin(
            //요청에 대한 응답을 ForestLoginResponse객체로 반환받고 메소드 이름을 postForestLogin
            //@Header("Authorization") String token,
            //토큰 미인증으로 변환함
            @Body ForestLoginRequest forestLoginRequest
    );

    //Get요청 메소드(Main Activity에서 사용함)
    @GET("/user/rentalList")
    //SKHU forest를 통해 현재 대여신청목록 가져옴
    Call<RentalListResponse> getRentalList(
            //요청에 대한 응답을 RentalListResponse객체로 반환받고 메소드 이름을 getRentalList로
//            @Header("Authorization") String token
//            토큰 요청 삭제처리
    );
    //Get요청 메소드(Main Activity에서 사용함)
    @GET("/students/own")
    //토큰에 대한 사용자 정보 조회
    Call<UserInfoResponse> getUserInfo(
            //요청에 대한 응답을 UserInfoResPonse객체로 반환받고 메소드 이름을 getUserInfo로
//            @Header("Authorization") String token
            //토큰 삭제처리
    );
    //Get요청 메소드(Select Activity에서 사용함)
    @GET("/buildings/{buildingNumber}/classrooms")
    //건물번호에 따라 대여할수있는 강의실 목록 가져옴
    Call<ClassRoomResponse> getClassRoomList(
            //요청에 대한 응답을 ClassRoomResponse객체로 반환받고 메소드 이름을 getClassRoomList로
//            @Header("Authorization") String token,
            //토큰 삭제처리
            @Path("buildingNumber") int bundingNumber
    );

    //Delete요청 메소드(Main Activity에서 사용함)
    @DELETE("/user/rental/{idx}")
    Call<DeleteResponse> deleteRentalList(
            @Path("idx") int idx
    );


}
