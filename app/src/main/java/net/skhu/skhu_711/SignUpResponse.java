package net.skhu.skhu_711;

public class SignUpResponse {
    int code;
    String status;
    String message;
    //어차피 data값 null값만 받아오므로 기존꺼 사용
    LoginData data;

    public SignUpResponse(int code, String status, String message, LoginData data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }
}
