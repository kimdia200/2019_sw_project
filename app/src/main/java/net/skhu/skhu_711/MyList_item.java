package net.skhu.skhu_711;

public class MyList_item {

    int index;
    RentalDate rentalDate;
    String rentalState, lectureCode;
    Boolean cancle;

    MyList_item(int index, RentalDate rentalDate, String rentalState, String lectureCode, Boolean cancle){

        this.index = index;
        this.rentalDate = rentalDate;
        this.rentalState = rentalState;
        this.lectureCode = lectureCode;
        this.cancle = cancle;

    }

    public int getIndex() {
        return index;
    }

    public RentalDate getRentalDate() {
        return rentalDate;
    }

    public String getRentalState() {
        return rentalState;
    }

    public String getLectureCode() {
        return lectureCode;
    }

    public Boolean getCancle() {
        return cancle;
    }
}
