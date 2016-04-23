package me.service;

import me.util.Strings;

/**
 * Created by chn on 15/12/18.
 */
public class PayByBankAccount extends PayBy {

    String bankAccount;
    public String getBankAccount() {
        return bankAccount;
    }
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public PayByBankAccount() {
        this.bankAccount = Strings.EMPTY;
    }

    public PayByBankAccount(long empId, String bankAccount) {
        super(empId);
        this.bankAccount = bankAccount;
    }

    public void pay(double sum) {

    }
}

