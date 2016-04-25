package me.service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.annotation.WhenCreated;
import com.avaje.ebean.annotation.WhenModified;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

/**
 * Created by chn on 16/1/23.
 */
@Entity
public class Employee {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Id
    private long empId;
    public long getEmpId() {
        return empId;
    }
    public void setEmpId(long empId) {
        this.empId = empId;
    }

    @Column
    private String bankAccount;
    public String getBankAccount() {
        return bankAccount;
    }
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
        if(payBy instanceof PayByBankAccount) {
            ((PayByBankAccount) payBy).setBankAccount(bankAccount);
        }
    }

    @Column
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column
    private String addr;
    public String getAddr() {
        return addr;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }


    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Affiliation> affiliations;
    public Set<Affiliation> getAffiliations() {
        return affiliations;
    }
    public void setAffiliations(Set<Affiliation> affiliations) {
        this.affiliations = affiliations;
    }


    //@PreRemove
    public void preRemove() {
        for (Affiliation aff : this.affiliations) {
            aff.getMembers().remove(this);
        }
        Ebean.save(this);
    }

    private PayBy payBy = new PayByBankAccount();
    public PayBy getPayBy() {
        return payBy;
    }
    public void setPayBy(PayBy payBy) {
        this.payBy = payBy;
    }
    @Column(length = 2048)
    private byte[] payByBinary;
    public byte[] getPayByBinary() {
        return payByBinary;
    }
    public void setPayByBinary(byte[] payByBinary) {
        this.payByBinary = payByBinary;
    }


    private PayClassification payClassification = null;
    public PayClassification getPayClassification() {
        return payClassification;
    }
    public void setPayClassification(PayClassification payClassification) {
        this.payClassification = payClassification;
    }
    @Column(length = 2048)
    private byte[] payClassificationBinary;
    public byte[] getPayClassificationBinary() {
        return payClassificationBinary;
    }
    public void setPayClassificationBinary(byte[] payClassificationBinary) {
        this.payClassificationBinary = payClassificationBinary;
    }

//    @Column(length = 2048)
//    private String payByBinary;
//    public String getPayByBinary() {
//        return payByBinary;
//    }
//    public void setPayByBinary(String payByBinary) {
//        this.payByBinary = payByBinary;
//    }
//
//    @Column(length = 2048)
//    private String payClassificationBinary;
//    public String getPayClassificationBinary() {
//        return payClassificationBinary;
//    }
//    public void setPayClassificationBinary(String payClassificationBinary) {
//        this.payClassificationBinary = payClassificationBinary;
//    }

    private double calcSum() {
        double affCharge = 0.;
        for (Affiliation aff : affiliations) {
            affCharge += aff.calcCharge();
        }
        return getPayClassification().calcSalary() - affCharge;
    }

    @WhenCreated
    Timestamp gmtCreate;

    @WhenModified
    Timestamp gmtModify;

    public boolean isPayDay(Date payDay) {
        return payClassification.getPaySchedule().isPayDay(payDay);
    }

    public void payDay(PayCheck pc) {
        //TODO no implementation
    }
}
