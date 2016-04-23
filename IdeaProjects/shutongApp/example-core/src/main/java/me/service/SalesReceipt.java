package me.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by chn on 16/4/22.
 */
@Entity
public class SalesReceipt {

    @Column
    long empId;

    @Column
    double amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public SalesReceipt(long empId, Date date, double amount) {
        this.empId = empId;
        this.date = date;
        this.amount = amount;
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
