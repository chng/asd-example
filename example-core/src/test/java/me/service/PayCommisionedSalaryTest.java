package me.service;

import me.BaseTest;
import me.db.query.GpayrollDataBase;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by chn on 16/4/22.
 */
public class PayCommisionedSalaryTest extends BaseTest {

    @Test
    public void test() throws ParseException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        long empId = 5L;
        AddCommisionedEmployee at = new AddCommisionedEmployee(empId, "CommisionedEmployee001", "Addr is null", 800.);
        at.execute();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SalesReceiptTransaction tt;
        tt = new SalesReceiptTransaction(
                empId,
                dateFormat.parse("2015-10-10 12:12:12"),
                100.);
        tt.execute();

        tt = new SalesReceiptTransaction(
                empId,
                dateFormat.parse("2015-10-10 15:12:12"),
                100.);
        tt.execute();

        tt = new SalesReceiptTransaction(
                4L,
                dateFormat.parse("2015-10-10 15:12:12"),
                100.);
        tt.execute();

        Employee employee = GpayrollDataBase.getEmployeeById(empId);
        assert(employee!=null);

        CommisionedSalary pc = (CommisionedSalary) employee.getPayClassification();
        assert(pc!=null);
        assert(pc.getSalaryPerMonth()==800.);
        assert(pc.getProfitFactor()==0.5);
        assert(pc.getEmpId()==empId);
        assert(pc.calcSalary()==pc.getSalaryPerMonth());
        assert(pc.calcSalary(dateFormat.parse("2015-10-10 15:12:12"))==pc.getSalaryPerMonth()+200*pc.getProfitFactor());

    }

}
