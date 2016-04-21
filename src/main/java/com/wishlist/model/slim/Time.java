package com.wishlist.model.slim;

import java.io.Serializable;

/**
 * Created by psundriyal on 4/20/16.
 */
public class Time implements Serializable{

    int hour;
    int minute;

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
