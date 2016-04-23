package me.service;

import com.avaje.ebean.annotation.WhenCreated;
import com.avaje.ebean.annotation.WhenModified;
import me.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by chn on 16/1/23.
 */
@Entity
public class Employee {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Id
    private long empId;
    @Column
    private String bankAccount;

    public long getEmpId() {
        return empId;
    }
    public void setEmpId(long empId) {
        this.empId = empId;
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


    private Affiliation affiliation = new AffiliationNull();
    public Affiliation getAffiliation() {
        return affiliation;
    }
    public void setAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
    }

    @Column
    private String affiliationName = affiliation.toString();
    public String getAffiliationName() {
        return affiliationName;
    }
    public void setAffiliationName(String affiliationName) {
        this.affiliationName = affiliationName;
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


    public double calcSum() {
        return getPayClassification().calcSalary() - getAffiliation().calcDues();
    }

    public void pay() {
        if(!paySchedule.isPayDay()) {
            return;
        }
        double sum = calcSum();
        payBy.pay(sum);
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
        if(payBy instanceof PayByBankAccount) {
            ((PayByBankAccount) payBy).setBankAccount(bankAccount);
        }
    }

    @WhenCreated
    Timestamp gmtCreate;

    @WhenModified
    Timestamp gmtModify;
}
