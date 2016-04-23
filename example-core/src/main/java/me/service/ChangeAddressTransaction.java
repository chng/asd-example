package me.service;

/**
 * Created by chn on 16/4/22.
 */
public class ChangeAddressTransaction extends ChangeEmployeeTransaction {

    public ChangeAddressTransaction(long empId, String name) {
        super(empId);
        setName(name);
    }

    String addr;
    public ChangeAddressTransaction setName(String name) {
        this.addr = name;
        return this;
    }

    public void update(Employee employee) {
        employee.setAddr(addr);
    }
}
