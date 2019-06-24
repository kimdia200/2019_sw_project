package net.skhu.skhu_711;

import java.time.LocalDate;
import java.util.Date;

public class RentalDate {

    int startTime;
    int endTime;
    LocalDate rentalDate;


    RentalDate(int startTime, int endTime, LocalDate rentalDate){
        this.startTime = startTime;
        this.endTime = endTime;
        this.rentalDate = rentalDate;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }
}
