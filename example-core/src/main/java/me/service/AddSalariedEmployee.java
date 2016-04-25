package me.service;

import me.db.query.GpayrollDataBase;
import me.util.Strings;

/**
 * Created by chn on 16/1/23.
 */
public class AddSalariedEmployee implements Transaction{

    Employee employee;

    public AddSalariedEmployee(long empId, String name, String address, double salaryPerMonth) {
        employee = new Employee();
        employee.setEmpId(empId);
        employee.setName(name);
        employee.setAddr(address);

        String bankAccount = Strings.EMPTY;

        //employee.setPaySchedule(new MonthlyPaySchedule());
        employee.setPayBy(new PayByPost(empId, address));

        SalariedSalary salariedSalary = new SalariedSalary(empId, salaryPerMonth);
        salariedSalary.setPaySchedule(new MonthlyPaySchedule());
        employee.setBankAccount(bankAccount);
        employee.setPayClassification(salariedSalary);

    }

    public void execute() {
        GpayrollDataBase.addEmployee(employee);
    }
}
