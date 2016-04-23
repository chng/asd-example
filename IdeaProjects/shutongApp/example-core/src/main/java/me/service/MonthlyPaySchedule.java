package me.service;

import me.util.DateUtil;

import java.util.Date;

/**
 * Created by chn on 16/1/23.
 */
public class MonthlyPaySchedule implements PaySchedule {

    public boolean isPayDay() {
        return DateUtil.isLastDayOfMonth(new Date());
    }

    @Override
    public String toString() {
        return this.getClass().getCanonicalName();
    }
}
