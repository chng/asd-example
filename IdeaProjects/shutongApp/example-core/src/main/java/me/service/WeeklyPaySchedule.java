package me.service;

import me.util.DateUtil;

import java.util.Date;

/**
 * Created by chn on 16/4/22.
 */
public class WeeklyPaySchedule implements PaySchedule {

    public boolean isPayDay() {
        return DateUtil.isLastDayOfWeek(new Date());
    }
}
