package me.service;

import me.BaseTest;
import me.db.query.GpayrollDataBase;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by chn on 16/2/1.
 */
public class PayHourlySalaryTest extends BaseTest {

    @Test
    public void test() throws ParseException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        long empId = 6L;
        AddHourlyEmployee at = new AddHourlyEmployee(empId, "Kill", "addr", 20);
        at.execute();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        TimeCardTransaction tt;
        tt = new TimeCardTransaction(
                dateFormat.parse("2015-10-13 21:12:12"),
                dateFormat.parse("2015-10-14 21:12:12"),
                empId);
        tt.execute();

        tt = new TimeCardTransaction(
                dateFormat.parse("2015-10-15 01:12:12"),
                dateFormat.parse("2015-10-15 09:12:12"),
                empId);
        tt.execute();

        Employee employee = GpayrollDataBase.getEmployeeById(empId);
        assert(employee!=null);

        HourlySalary pc = (HourlySalary) employee.getPayClassification();
        assert(pc!=null);
        assert(pc.getSalaryPerHour()==20);
        assert(pc.getEmpId()==empId);
        assert(pc.calcSalary(dateFormat.parse("2015-10-31 00:00:00"))==0);
        assert(pc.calcSalary(dateFormat.parse("2015-10-17 00:00:00"))==32*pc.getSalaryPerHour());
    }
}
