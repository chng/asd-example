package me.service;

import me.db.query.GpayrollDataBase;

import java.util.Date;

/**
 * Created by chn on 16/2/1.
 */
public class TimeCardTransaction implements Transaction {


    TimeCard timeCard;

    public TimeCardTransaction(Date from, Date to, long empId) {
        timeCard = new TimeCard(from, to,empId);
    }


    public void execute() {
        GpayrollDataBase.addTimeCard(timeCard);
    }
}
