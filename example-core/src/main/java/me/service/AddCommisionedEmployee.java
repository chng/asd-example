package me.service;

import me.db.query.GpayrollDataBase;
import me.util.Strings;

/**
 * Created by chn on 16/4/22.
 */
public class AddCommisionedEmployee implements Transaction {

    Employee employee = new Employee();

    double DEFAULT_PROFIT_FACTOR = 0.5;

    public AddCommisionedEmployee(long empId, String name, String addr, double salaryPerMonth, double profitFactor) {
        init(empId, name, addr, salaryPerMonth, profitFactor);
    }

    public AddCommisionedEmployee(long empId, String name, String addr, double salaryPerMonth) {
        init(empId, name, addr, salaryPerMonth, DEFAULT_PROFIT_FACTOR);
    }

    private void init(long empId, String name, String addr, double salaryPerMonth, double profitFactor) {
        employee.setEmpId(empId);
        employee.setName(name);
        employee.setAddr(addr);

        String bankAccount = Strings.EMPTY;

//        employee.setPaySchedule(new MonthlyPaySchedule());
        employee.setPayBy(new PayByPost(empId, addr));

        CommisionedSalary commisionedSalary = new CommisionedSalary(empId, profitFactor);
        commisionedSalary.setSalaryPerMonth(salaryPerMonth);
        commisionedSalary.setPaySchedule(new MonthlyPaySchedule());
        employee.setBankAccount(bankAccount);
        employee.setPayClassification(commisionedSalary);
    }

    public void execute() {
        GpayrollDataBase.addEmployee(employee);
    }
}
