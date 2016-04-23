package me.service;

import me.db.query.GpayrollDataBase;

import java.util.Date;

/**
 * Created by chn on 16/4/22.
 */
public class SalesReceiptTransaction implements Transaction {

    SalesReceipt salesReceipt;

    public SalesReceiptTransaction(long empId, Date date, double amount) {
        salesReceipt = new SalesReceipt(empId, date, amount);
    }


    public void execute() {
        GpayrollDataBase.addSalesReceipt(salesReceipt);
    }
}
