package me.service;

import me.db.query.GpayrollDataBase;
import me.util.DateUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by chn on 16/1/23.
 */
public class CommisionedSalary extends SalariedSalary {

    public CommisionedSalary() {super(0L);}

    public CommisionedSalary(long id, double salaryPerMonth, double profitFactor) {
        super(id);
        this.setSalaryPerMonth(salaryPerMonth);
        this.setProfitFactor(profitFactor);
    }

    public CommisionedSalary(long id, double profitFactor) {
        super(id);
        this.profitFactor = profitFactor;
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

    public double calcSalary(Date whichMonth) {
        // 计算whichMonth的salary
        return calcSalary(
                DateUtil.startOfCurrentMonth(whichMonth),
                DateUtil.startOfNextMonth(whichMonth)
        );
    }


    public double calcSalary(Date from, Date to) {
        // 计算whichMonth的salary
        List<SalesReceipt> salesReceipts = GpayrollDataBase.getSalesReceipt(empId, from, to);
        double sum = 0.;
        for(SalesReceipt sp: salesReceipts) {
            sum += sp.getAmount();
        }
        return getSalaryPerMonth() + sum*getProfitFactor();
    }
}
