package me.service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by chn on 16/1/23.
 */
public abstract class PayClassification implements Serializable {

    long empId;
    public long getEmpId() {
        return empId;
    }
    public void setEmpId(long empId) {
        this.empId = empId;
    }

    private PaySchedule paySchedule;
    public PaySchedule getPaySchedule() {
        return paySchedule;
    }
    public void setPaySchedule(PaySchedule paySchedule) {
        this.paySchedule = paySchedule;
    }

    @Resource
    MonthlyPaySchedule monthlyPaySchedule;

    public PayClassification(long id) {
        empId = id;
        setPaySchedule(monthlyPaySchedule);
    }

    abstract double calcSalary();

    abstract double calcSalary(Date date);

    public String toString() {
        return this.getClass().getCanonicalName();
    }
}
