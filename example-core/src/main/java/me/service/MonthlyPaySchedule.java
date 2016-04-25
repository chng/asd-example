package me.service;

import me.util.DateUtil;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by chn on 16/1/23.
 */
@Component
public class MonthlyPaySchedule implements PaySchedule {

    public boolean isPayDay(Date date) {
        return DateUtil.isLastDayOfMonth(date);
    }

    public boolean isPayDay() {
        return DateUtil.isLastDayOfMonth(new Date());
    }

    public Date payStartDate(Date payDay) {
        return DateUtil.startOfCurrentMonth(payDay);
    }

    public Date payEndDate(Date payDay) {
        return DateUtil.endOfCurrentMonth(payDay);
    }
}
