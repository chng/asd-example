package me.service;

import java.io.Serializable;

/**
 * Created by chn on 15/12/18.
 */
public abstract class PayBy implements Serializable {

    long empId = 0;
    public long getEmpId() {
        return empId;
    }
    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public PayBy() {}
    public PayBy(long empId) {setEmpId(empId);}


    abstract void pay(double sum);

    @Override
    public String toString() {
        return this.getClass().getCanonicalName();
    }
}
