package com.rfb.demo.rxjavatest.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {
    public static String formatString = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    public static long getTimeByString(String serverTime) {
        return getTimeByString(serverTime, Locale.getDefault());
    }

    public static String getTimeString(long time) {
        return getTimeString(time, Locale.getDefault());
    }

    public static String getTimeString(long pTime, Locale pLocale) {
        if (pTime <= 0) {
            return "";
        }

        Date date = new Date(pTime);
        SimpleDateFormat sdf = new SimpleDateFormat(formatString, pLocale);
        return sdf.format(date);
    }

    public static long getTimeByString(String pTimeString, Locale pLocale) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formatString, pLocale);
            Date dt = sdf.parse(pTimeString);
            return dt.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
