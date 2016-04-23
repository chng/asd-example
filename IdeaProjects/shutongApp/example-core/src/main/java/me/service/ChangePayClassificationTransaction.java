package me.service;

/**
 * Created by chn on 16/4/22.
 */
public class ChangePayClassificationTransaction extends ChangeEmployeeTransaction {


    PayClassification payClassfication;
    public PayClassification getPayClassfication() {
        return payClassfication;
    }
    public void setPayClassfication(PayClassification payClassfication) {
        this.payClassfication = payClassfication;
    }

    public ChangePayClassificationTransaction(long empId, PayClassification payClassfication) {
        super(empId);
        setPayClassfication(payClassfication);
    }

    public void update(Employee employee) {
        employee.setPayClassification(payClassfication);
    }
}
