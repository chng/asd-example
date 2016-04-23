package me.service;

/**
 * Created by chn on 16/4/22.
 */
public class ChangePayByTransaction extends ChangeEmployeeTransaction {

    PayBy payBy;

    public ChangePayByTransaction(long empId, PayBy pb) {
        super(empId);
        payBy = pb;
    }

    public void update(Employee employee) {
        employee.setPayBy(payBy);
    }
}
