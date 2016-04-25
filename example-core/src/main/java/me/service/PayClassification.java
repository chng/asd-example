package me.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by chn on 16/1/23.
 */
public class PayClassification implements Serializable {

    long empId;
    public long getEmpId() {
        return empId;
    }
    public void setEmpId(long empId) {
        this.empId = empId;
    }


    @Resource(name = "monthlyPaySchedule")
    private PaySchedule paySchedule = (PaySchedule) new ClassPathXmlApplicationContext("classpath*:/spring-core.xml").getBean("monthlyPaySchedule");
    public PaySchedule getPaySchedule() {
        return paySchedule;
    }
    public void setPaySchedule(PaySchedule paySchedule) {
        this.paySchedule = paySchedule;
    }

    public PayClassification(long id) {
        empId = id;
    }

    double calcSalary() {
        return 0;
    }

    double calcSalary(Date date) {
        return 0;
    }
}
