package me.service;

/**
 * Created by chn on 16/1/23.
 */
public class PayClassification {

    long empId;

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public PayClassification(long id) {
        empId = id;
    }

    double calcSalary() {
        return 0;
    }

    public String toString() {
        return this.getClass().getCanonicalName();
    }
}
