package me.service;

import com.avaje.ebean.Ebean;

/**
 * Created by chn on 16/2/1.
 */
public class DeleteEmployeeTransaction implements Transaction {

    long empId;
    public DeleteEmployeeTransaction(long empId) {
        this.empId = empId;
    }

    public void execute() {
        System.out.println(empId);
        Ebean.delete(Employee.class, empId);
    }
}
