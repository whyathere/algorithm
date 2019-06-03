package com.tutorial;

import org.apache.commons.lang.time.FastDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeFormate {
    public static void main(String[] args) throws ParseException {

        //将时区转换成伦敦时间
//        long time = account.getDateOfBirth().getTime();

        long time = new Date().getTime();
        SimpleDateFormat londonSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 伦敦
        londonSdf.setTimeZone(TimeZone.getTimeZone("Europe/London"));  // 设置伦敦时区
        String format = londonSdf.format(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = simpleDateFormat.parse(format);

        Date date = changeDateToLondon(new Date());
        System.out.println(date);

    }


    public static Date changeDateToLondon(Date dateOfBirth) {
        Date date = null;
        try {//当前生日加一天
            long time = dateOfBirth.getTime();
            SimpleDateFormat londonSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 伦敦
            londonSdf.setTimeZone(TimeZone.getTimeZone("Europe/London"));  // 设置伦敦时区
            String format = londonSdf.format(time);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = simpleDateFormat.parse(format);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}
