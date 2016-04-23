package me.service;

import me.db.query.GpayrollDataBase;
import me.util.Strings;

import javax.annotation.Resource;

/**
 * Created by chn on 16/2/1.
 */
public class AddHourlyEmployee implements Transaction {

    Employee employee;

    public AddHourlyEmployee(long empId, String name, String addr, double hourlySalaryMount) {
        employee = new Employee();
        employee.setEmpId(empId);
        employee.setAddr(addr);
        employee.setName(name);
        employee.setBankAccount(Strings.EMPTY);

        employee.setPaySchedule(new MonthlyPaySchedule());
        employee.setPayBy(new PayByBankAccount(empId, Strings.EMPTY));

        HourlySalary hourlySalary = new HourlySalary(empId);
        hourlySalary.setSalaryPerHour(hourlySalaryMount);
        employee.setPayClassification(hourlySalary);
    }


    @Resource
    GpayrollDataBase gpayrollDataBase;

    public void execute() {
        GpayrollDataBase.addEmployee(employee);
    }
}
