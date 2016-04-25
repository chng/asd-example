package me.service;

import me.util.DateUtil;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by chn on 16/4/22.
 */
@Component
public class WeeklyPaySchedule implements PaySchedule {

    public boolean isPayDay(Date date) {
        return DateUtil.isLastDayOfWeek(date);
    }

    public boolean isPayDay() {
        return DateUtil.isLastDayOfWeek(new Date());
    }
}
