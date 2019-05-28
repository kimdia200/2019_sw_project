package net.skhu.skhu_711;

public class Building_item {
    String name;
    String code;
    int img_src;

    public Building_item(String name, String code,int img_src){
        this.name = name;
        this.code = code;
        this.img_src = img_src;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getImg_src() {
        return img_src;
    }
}
