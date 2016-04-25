package me.service;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by chn on 15/12/21.
 */
public interface PaySchedule extends Serializable {
    boolean isPayDay(Date date);
    boolean isPayDay();

    Date payStartDate(Date payDay);

    Date payEndDate(Date payDay);
}
