package me.service;

import me.db.query.GpayrollDataBase;
import me.util.Strings;

/**
 * Created by chn on 16/4/22.
 */
public class AddCommisionedEmployee implements Transaction {

    Employee employee = new Employee();


    public AddCommisionedEmployee(long empId, String name, String addr, double salaryPerMonth) {
        employee.setEmpId(empId);
        employee.setName(name);
        employee.setAddr(addr);

        String bankAccount = Strings.EMPTY;

        employee.setPaySchedule(new MonthlyPaySchedule());
        employee.setPayBy(new PayByPost(empId, addr));

        CommisionedSalary commisionedSalary = new CommisionedSalary(empId, 0.5);
        commisionedSalary.setSalaryPerMonth(salaryPerMonth);
        employee.setBankAccount(bankAccount);
        employee.setPayClassification(commisionedSalary);
    }

    public void execute() {
        GpayrollDataBase.addEmployee(employee);
    }
}
