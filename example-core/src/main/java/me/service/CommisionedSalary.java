package me.service;

import me.db.query.GpayrollDataBase;

import java.util.Date;
import java.util.List;

/**
 * Created by chn on 16/1/23.
 */
public class CommisionedSalary extends SalariedSalary {

    public CommisionedSalary(long id, double salaryPerMonth, double profitFactor) {
        super(id, salaryPerMonth);
        this.setProfitFactor(profitFactor);
    }


    double profitFactor = 0.0;
    public double getProfitFactor() {
        return profitFactor;
    }
    public void setProfitFactor(double profitFactor) {
        this.profitFactor = profitFactor;
    }

    public double calcSalary() {
        return calcSalary(new Date());
    }

    public double calcSalary(Date payDay) {
        // payDay是发薪日
        // Salary从合适开始计, 由paySchedule给出.
        Date startTime = getPaySchedule().payStartDate(payDay);
        Date endTime   = getPaySchedule().payEndDate(payDay);
        return calcSalary(startTime, endTime);
    }


    public double calcSalary(Date from, Date to) {
        List<SalesReceipt> salesReceipts = GpayrollDataBase.getSalesReceipt(empId, from, to);
        double sum = 0.;
        for(SalesReceipt sp: salesReceipts) {
            sum += sp.getAmount();
        }
        return getSalaryPerDue() + sum*getProfitFactor();
    }
}
