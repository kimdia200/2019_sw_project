package net.skhu.skhu_711;

import java.util.List;

public class DeleteResponse {
    int code;
    String status;
    String message;
    List<ClassRoom_item> data=null;

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

    public List<ClassRoom_item> getData() {
        return data;
    }

    public void setData(List<ClassRoom_item> data) {
        this.data = data;
    }

    public DeleteResponse(int code, String status, String message, List<ClassRoom_item> data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
