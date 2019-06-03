package com.someaction;

import java.util.Calendar;
import java.util.Date;

public class TimeVal {
    public static void main(String[] args) {
        Calendar ca = Calendar.getInstance();
        int dayOfWeek = ca.get(Calendar.DAY_OF_WEEK);
        int hourOfDay = ca.get(Calendar.HOUR_OF_DAY);
        Date time = ca.getTime();
        System.out.println(hourOfDay);
    }
}
