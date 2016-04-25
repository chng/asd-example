package me.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by chn on 16/4/22.
 */
public class DateUtil {
    static int monthDay0[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static int monthDay1[] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    public static boolean isLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int monthDay[] = isLeapYear(year) ? monthDay1 : monthDay0;
        //System.out.println("cal.get(Calendar.YEAR) = " + cal.get(Calendar.YEAR)); //2011 ...
        //System.out.println("cal.get(Calendar.MONTH) = " + cal.get(Calendar.MONTH)); //0..11
        //System.out.println("cal.get(Calendar.DATE) = " + cal.get(Calendar.DATE)); //1..31
        return monthDay[cal.get(Calendar.MONTH)] == cal.get(Calendar.DATE);
    }

    public static boolean isLastDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY;
    }

    public static Date startOfCurrentMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date startOfNextMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startOfCurrentMonth(date));
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        if(month++ == Calendar.DECEMBER) {
            month = 1;
            year++;
        }
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);
        return cal.getTime();
    }


    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static Date parseFormat(String dateStr) throws ParseException {
        return dateFormat.parse(dateStr);
    }
}
