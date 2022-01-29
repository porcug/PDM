package com.example.fooddeliveryapp.models;

import androidx.annotation.NonNull;

import java.time.DayOfWeek;

public class WorkingInterval {


   public enum Days{Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday};
    Days day;
    int start;
    int stop;

    public  Days getDay() {
        return  day;
    }

    public void setDay( Days day) {
        this.day = day;

    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {

            this.start = start%24*60;
    }

    public int getStop() {
        return stop;
    }

    public void setStop(int stop) {
        this.stop = stop;
    }

}
