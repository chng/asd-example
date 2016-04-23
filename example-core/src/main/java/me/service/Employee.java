package me.service;

import com.avaje.ebean.annotation.WhenCreated;
import com.avaje.ebean.annotation.WhenModified;
import me.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.sql.Timestamp;
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


    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Affiliation> affiliations;
    public Set<Affiliation> getAffiliations() {
        return affiliations;
    }
    public void setAffiliations(Set<Affiliation> affiliations) {
        this.affiliations = affiliations;
    }


    private PayBy payBy = new PayByBankAccount();
    public PayBy getPayBy() {
        return payBy;
    }
    public void setPayBy(PayBy payBy) {
        this.payBy = payBy;
    }
    @Column
    private String payByJson = payBy.toString();
    public String getPayByJson() {
        return payByJson;
    }
    public void setPayByJson(String payByJson) {
        this.payByJson = payByJson;
    }

    private PayClassification payClassification = null;
    public PayClassification getPayClassification() {
        return payClassification;
    }
    public void setPayClassification(PayClassification payClassification) {
        this.payClassification = payClassification;
    }
    @Column
    private String payClassificationJson = Strings.EMPTY;
    public String getPayClassificationJson() {
        return payClassificationJson;
    }
    public void setPayClassificationJson(String payClassificationJson) {
        this.payClassificationJson = payClassificationJson;
    }


    private PaySchedule paySchedule = null;
    public PaySchedule getPaySchedule() {
        return paySchedule;
    }
    public void setPaySchedule(PaySchedule paySchedule) {
        this.paySchedule = paySchedule;
    }
    @Column
    private String payScheduleJson = Strings.EMPTY;
    public String getPayScheduleJson() {
        return payScheduleJson;
    }
    public void setPayScheduleJson(String payScheduleJson) {
        this.payScheduleJson = payScheduleJson;
    }


    private double calcSum() {
        double affCharge = 0.;
        for(Affiliation aff: affiliations) {
            affCharge += aff.calcCharge();
        }
        return getPayClassification().calcSalary() - affCharge;
    }

    public void pay() {
        if(!paySchedule.isPayDay()) {
            return;
        }
        double sum = calcSum();
        payBy.pay(sum);
    }


    @WhenCreated
    Timestamp gmtCreate;

    @WhenModified
    Timestamp gmtModify;
}
