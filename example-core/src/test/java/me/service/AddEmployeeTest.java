package me.service;

import me.BaseTest;
import me.db.query.GpayrollDataBase;
import me.util.JsonUtil;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by chn on 16/1/23.
 */
public class AddEmployeeTest extends BaseTest {

    @Resource
    GpayrollDataBase gpayrollDataBase;

    @Test
    public void testAddHourlyEmployee() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Transaction t = new AddHourlyEmployee(1L, "Bob", "Home", 200.0);
        t.execute();

        Employee e = gpayrollDataBase.getEmployeeById(1L);
        System.out.println(JsonUtil.toJSONString(e));
        assert(e.getName().equals("Bob"));
        assert(e.getPayBy() instanceof PayByBankAccount);
        assert(e.getPayClassification() instanceof HourlySalary);
        assert(e.getPayClassification().getPaySchedule() instanceof WeeklyPaySchedule);
    }

    @Test
    public void testAddSalariedEmployee() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Transaction t = new AddSalariedEmployee(2L, "Bob", "Home", 1000.00);
        t.execute();

        Employee e = gpayrollDataBase.getEmployeeById(2L);
        System.out.println(JsonUtil.toJSONString(e));
        assert(e.getName().equals("Bob"));
        assert(e.getPayClassification() instanceof SalariedSalary);
        assert(e.getPayBy() instanceof PayByPost);
        assert(e.getPayClassification().getPaySchedule() instanceof MonthlyPaySchedule);
    }

    @Test
    public void testAddCommisionedEmployee() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Transaction t = new AddCommisionedEmployee(3L, "Bob", "Home", 1000.00);
        t.execute();

        Employee e = gpayrollDataBase.getEmployeeById(3L);
        System.out.println(JsonUtil.toJSONString(e));
        assert(e.getName().equals("Bob"));
        assert(e.getPayClassification() instanceof SalariedSalary);
        assert(e.getPayBy() instanceof PayByPost);
        assert(e.getPayClassification().getPaySchedule() instanceof MonthlyPaySchedule);
    }
}
