package me.service;

import java.util.Date;
/**
 * Created by chn on 16/4/24.
 */
public class PayCheck {

    Date payDay;
    double grossPay;

    public PayCheck(Date payDay, double grossPay) {
        this.payDay = payDay;
        this.grossPay = grossPay;
    }

    public Date getPayDay() {
        return payDay;
    }

    public double getGrossPay() {
        return grossPay;
    }
}
