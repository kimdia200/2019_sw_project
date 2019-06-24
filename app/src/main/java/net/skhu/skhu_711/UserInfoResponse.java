package net.skhu.skhu_711;

public class UserInfoResponse {

    int code;
    String status;
    String message;
    UserInfoData data;

    public UserInfoResponse(int code, String status, String message, UserInfoData data) {
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

    public UserInfoData getData() {
        return data;
    }

    public void setData(UserInfoData data) {
        this.data = data;
    }
}
