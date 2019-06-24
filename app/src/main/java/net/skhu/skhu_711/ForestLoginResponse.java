package net.skhu.skhu_711;

public class ForestLoginResponse {
    String code;
    String status;
    String message;

    //어차피 data값은 null값만 받아오기때문에 그냥 기존loginData사용함
    LoginData data;

    public ForestLoginResponse(String code, String status, String message, LoginData data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
