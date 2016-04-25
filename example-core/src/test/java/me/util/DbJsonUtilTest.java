package me.util;

import me.service.Employee;
import org.junit.Test;

/**
 * Created by chn on 16/4/25.
 */
public class DbJsonUtilTest {

    @Test
    public void test() {
        Employee employee = new Employee();
        System.out.println(DBJsonUtil.toJSONString(employee));
    }

}
