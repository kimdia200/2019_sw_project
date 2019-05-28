package net.skhu.skhu_711;

public class Facility_item {
    String fName,fCode,fCapacity;
    int fImg_src;

    public Facility_item( String fName, String fCode, String fCapacity, int fImg_src){
        this.fName = fName;
        this.fCode = fCode;
        this.fCapacity = fCapacity;
        this.fImg_src = fImg_src;
    }

    public String getfName() {
        return fName;
    }

    public String getfCode() {
        return fCode;
    }

    public String getfCapacity() {
        return fCapacity;
    }

    public int getfImg_src() {
        return fImg_src;
    }
}
