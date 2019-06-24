package net.skhu.skhu_711;

import java.util.ArrayList;
import java.util.List;

public class RentalListResponse {
    int code;
    String status;
    String message;
    List<MyList_item> data=null;

    public RentalListResponse(int code, String status, String message, List<MyList_item> data) {
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

    public List<MyList_item> getData() {
        return data;
    }

    public void setData(List<MyList_item> data) {
        this.data = data;
    }
}
