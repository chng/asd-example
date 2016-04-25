package me.service;

import me.db.query.GpayrollDataBase;

import java.util.Date;
import java.util.List;

/**
 * Created by chn on 16/1/23.
 */
public class HourlySalary extends PayClassification{

    double salaryPerHour = 120;

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

    public double calcSalary(Date payDay) {
        // payDay是发薪日
        // Salary从合适开始计, 由paySchedule给出.
        Date startTime = getPaySchedule().payStartDate(payDay);
        Date endTime   = getPaySchedule().payEndDate(payDay);
        System.out.println("startTime = " + startTime);
        System.out.println("endTime = " + endTime);
        return calcSalary(startTime, endTime);
    }


    public double calcSalary(Date from, Date to) {
        // 计算从frmo到to的salary
        List<TimeCard> timeCards = GpayrollDataBase.getTimeCardByEmpId(empId, from, to);
        double sum = 0.;
        for(TimeCard timeCard: timeCards) {
            sum += timeCard.getHours();
        }
        return sum*getSalaryPerHour();
    }
}
