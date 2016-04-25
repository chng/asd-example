package me.service;

import java.util.Date;

/**
 * Created by chn on 16/1/23.
 */
public class SalariedSalary extends PayClassification {

    public SalariedSalary(long id, double salaryPerMonth) {
        super(id);
        this.salaryPerDue = salaryPerMonth;
    }

    public double getSalaryPerDue() {
        return salaryPerDue;
    }

    public void setSalaryPerDue(double salaryPerDue) {
        this.salaryPerDue = salaryPerDue;
    }

    private double salaryPerDue = 0.0;

    public double calcSalary() {
        return getSalaryPerDue();
    }

    public double calcSalary(Date whichMonth) {
        // 计算whichMonth的salary
        return calcSalary();
    }
}
