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
public class AffiliationCharge {

    @Column
    long empId;

    @Column
    long affId;

    @Temporal(TemporalType.TIMESTAMP)
    Date date;

    @Column
    double amount;



    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public long getAffId() {
        return affId;
    }

    public void setAffId(long affId) {
        this.affId = affId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
