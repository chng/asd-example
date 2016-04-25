package me.service;

import me.util.DateUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Created by chn on 16/4/24.
 */
public class PaySalaryByScheduleTest {

    static long hourlyEmpId = 8L;
    static long commisionedEmpId = 9L;
    static long salariedlyEmpId = 10L;

    @BeforeClass
    public static void addEmployee() {
        Transaction t;
        t = new AddHourlyEmployee(hourlyEmpId, "Bob", "Home", 200.0);
        t.execute();

        t = new AddCommisionedEmployee(commisionedEmpId, "Bob", "Home", 10000.0, 1);
        t.execute();

        t = new AddSalariedEmployee(salariedlyEmpId, "Bob", "Home", 12000.0);
        t.execute();
        //12000
    }


    @Test
    // 不同的PayClassification应该持有不同的PaySchedule.
    // 例如HourlySalary可以持有WeeklySchedule, 但SalariedSalary不可以
    public void testMonthlySchedulePay() throws ParseException, IllegalAccessException, ClassNotFoundException, InstantiationException {

        Transaction t = new TimeCardTransaction(
                DateUtil.parseFormat("2015-10-10 10:10:10.000"),
                DateUtil.parseFormat("2015-10-10 21:10:10.000"),
                hourlyEmpId
        );
        t.execute();
        t = new TimeCardTransaction(
                DateUtil.parseFormat("2015-10-11 10:10:10.000"),
                DateUtil.parseFormat("2015-10-11 21:10:10.000"),
                hourlyEmpId
        );
        t.execute();
        t = new TimeCardTransaction(
                DateUtil.parseFormat("2015-10-12 10:10:10.000"),
                DateUtil.parseFormat("2015-10-12 21:10:10.000"),
                hourlyEmpId
        );
        t.execute();
        //200 * (11+11)

        t = new SalesReceiptTransaction(
                commisionedEmpId,
                DateUtil.parseFormat("2015-10-10 10:10:10.000"),
                200
        );
        t.execute();
        t = new SalesReceiptTransaction(
                commisionedEmpId,
                DateUtil.parseFormat("2015-10-11 10:10:10.000"),
                200
        );
        t.execute();
        //10000+200+200

        Calendar cal = Calendar.getInstance();
        cal.set(2015, Calendar.OCTOBER, 31);
        PayDayTransaction pt = new PayDayTransaction(cal.getTime());
        pt.execute();
        PayCheck pc;
        pc = pt.getPayCheck(hourlyEmpId);
        assert(pc!=null);
        assert(pc.getPayDay().equals(cal.getTime()));
        System.out.println("pc.getGrossPay() = " + pc.getGrossPay());
        assert(pc.getGrossPay()==0);

        pc = pt.getPayCheck(commisionedEmpId);
        assert(pc!=null);
        assert(pc.getPayDay().equals(cal.getTime()));
        assert(pc.getGrossPay()==(10000+200+200));

        pc = pt.getPayCheck(salariedlyEmpId);
        assert(pc!=null);
        assert(pc.getPayDay().equals(cal.getTime()));
        assert(pc.getGrossPay()==12000);
    }

    @Test
    public void testWeeklyPay() throws ParseException {

        Calendar cal = Calendar.getInstance();
        cal.set(2015, Calendar.OCTOBER, 17);
        PayDayTransaction pt = new PayDayTransaction(cal.getTime());
        pt.execute();
        PayCheck pc;
        pc = pt.getPayCheck(hourlyEmpId);
        assert(pc!=null);
        assert(pc.getPayDay().equals(cal.getTime()));
        System.out.println("pc.getGrossPay() = " + pc.getGrossPay());
        assert(pc.getGrossPay()==(200 * (11+11)));

        //TODO 7天为一个发薪期, 还需考虑10-10 ~ 10-11(跨发薪期)的情况
    }
}
