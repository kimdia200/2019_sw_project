package net.skhu.skhu_711;

public class RentalListData {
    int idx;
    RentalDate rentalDate;
    String rentalState;
    String lectureCode;
    Boolean cancel;

    public RentalListData(int idx, RentalDate rentalDate, String rentalState, String lectureCode, Boolean cancel) {
        this.idx = idx;
        this.rentalDate = rentalDate;
        this.rentalState = rentalState;
        this.lectureCode = lectureCode;
        this.cancel = cancel;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public RentalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(RentalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public String getRentalState() {
        return rentalState;
    }

    public void setRentalState(String rentalState) {
        this.rentalState = rentalState;
    }

    public String getLectureCode() {
        return lectureCode;
    }

    public void setLectureCode(String lectureCode) {
        this.lectureCode = lectureCode;
    }

    public Boolean isCancel() {
        return cancel;
    }

    public void setCancel(Boolean cancel) {
        this.cancel = cancel;
    }
}
