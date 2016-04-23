package me.service;

import me.db.query.GpayrollDataBase;
import me.util.DateUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by chn on 16/1/23.
 */
public class HourlySalary extends PayClassification{

    double salaryPerHour = 120;

    public HourlySalary() {super(0L);}
    public HourlySalary(long id) {
        super(id);
    }

    public double getSalaryPerHour() {
        return salaryPerHour;
    }
    public void setSalaryPerHour(double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }


    @Override
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
        List<TimeCard> timeCards = GpayrollDataBase.getTimeCardByEmpId(empId, from, to);
        double sum = 0.;
        for(TimeCard timeCard: timeCards) {
            sum += timeCard.getHours();
        }
        return sum*getSalaryPerHour();
    }
}
