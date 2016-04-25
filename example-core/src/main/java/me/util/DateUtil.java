package me.util;

import sun.util.calendar.CalendarUtils;

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


    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    public static Date parseFormat(String dateStr) throws ParseException {
        return dateFormat.parse(dateStr);
    }

    private static boolean isLeapYear(int year) {
        //return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
        return CalendarUtils.isGregorianLeapYear(year);
    }

    public static boolean isLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int monthDay[] = isLeapYear(year) ? monthDay1 : monthDay0;
        return monthDay[cal.get(Calendar.MONTH)] == cal.get(Calendar.DATE);
    }

    // SUNDAY是第一天 = 1
    // SATURDAY是最后一天 = 7
    public static boolean isLastDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY;
    }

    public static Date startOfCurrentDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        dateFormat.format(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date endOfCurrentDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    public static Date startOfCurrentMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startOfCurrentDay(date));
        cal.set(Calendar.DATE, 1);
        return cal.getTime();
    }

    public static Date endOfCurrentMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(endOfCurrentDay(date));
        int year = cal.get(Calendar.YEAR);
        int monthDay[] = isLeapYear(year) ? monthDay1 : monthDay0;
        int month = cal.get(Calendar.MONTH);
        cal.set(Calendar.DATE, monthDay[month]);
        return cal.getTime();
    }


    public static Date startOfCurrentWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startOfCurrentDay(date));
        //millisecond(date/00:00:00) - millisecond(DayOfWeek - FirstDayOfWeek)
        return new Date(cal.getTime().getTime() - 1000*60*60*24*(cal.get(Calendar.DAY_OF_WEEK)-Calendar.SUNDAY));
    }

    public static Date endOfCurrentWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(endOfCurrentDay(date));
        //millisecond(date/23:59:59) + millisecond(EndDayOfWeek - DayOfWeek);
        return new Date(cal.getTime().getTime() + 1000*60*60*24*(Calendar.SATURDAY - cal.get(Calendar.DAY_OF_WEEK)));
    }


}
