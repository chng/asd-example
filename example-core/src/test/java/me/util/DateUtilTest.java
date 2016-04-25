package me.util;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chn on 16/4/22.
 */
public class DateUtilTest {
    @Test
    public void test() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = dateFormat.parse("2011-10-31 13:01:12.000");

        System.out.println(date);
        System.out.println(DateUtil.isLastDayOfMonth(date));
        System.out.println(DateUtil.isLastDayOfWeek(date));


        System.out.println(DateUtil.startOfCurrentDay(date));
        System.out.println(DateUtil.endOfCurrentDay(date));

        System.out.println(DateUtil.startOfCurrentWeek(date));
        System.out.println(DateUtil.endOfCurrentWeek(date));


        System.out.println(DateUtil.startOfCurrentMonth(date));
        System.out.println(DateUtil.endOfCurrentMonth(date));

    }
}
