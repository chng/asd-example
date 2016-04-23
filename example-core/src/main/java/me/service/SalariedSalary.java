package me.service;

/**
 * Created by chn on 16/1/23.
 */
public class SalariedSalary extends PayClassification {

    public SalariedSalary() {
        super(0L);
    }
    public SalariedSalary(long id) {
        super(id);
    }
    public SalariedSalary(long id, double salaryPerMonth) {
        super(id);
        this.salaryPerMonth = salaryPerMonth;
    }

    public double getSalaryPerMonth() {
        return salaryPerMonth;
    }

    public void setSalaryPerMonth(double salaryPerMonth) {
        this.salaryPerMonth = salaryPerMonth;
    }

    private double salaryPerMonth = 0.0;

    public double calcSalary() {
        return 0;
    }

}
