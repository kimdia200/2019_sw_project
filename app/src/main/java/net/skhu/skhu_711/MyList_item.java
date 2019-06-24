package net.skhu.skhu_711;

public class MyList_item {

    int idx;
    RentalDate rentalDate;
    String rentalState;
    String lectureCode;
    Boolean cancel;

    MyList_item(int idx, RentalDate rentalDate, String rentalState, String lectureCode, Boolean cancle){

        this.idx = idx;
        this.rentalDate = rentalDate;
        this.rentalState = rentalState;
        this.lectureCode = lectureCode;
        this.cancel = cancle;

    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public void setRentalDate(RentalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public void setRentalState(String rentalState) {
        this.rentalState = rentalState;
    }

    public void setLectureCode(String lectureCode) {
        this.lectureCode = lectureCode;
    }

    public void setCancle(Boolean cancel) {
        this.cancel = cancel;
    }

    public int getIndex() {
        return idx;
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

    public Boolean getCancel() {
        return cancel;
    }
}
