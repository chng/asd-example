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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dateFormat.parse("2011-12-03 12:12:12");
        System.out.println(date);
        System.out.println(DateUtil.isLastDayOfMonth(date));
        System.out.println(DateUtil.startOfCurrentMonth(date));
        System.out.println(DateUtil.startOfNextMonth(date));
    }

}
