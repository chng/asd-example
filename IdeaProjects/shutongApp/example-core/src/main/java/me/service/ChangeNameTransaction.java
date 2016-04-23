package me.service;

/**
 * Created by chn on 16/4/22.
 */
public class ChangeNameTransaction extends ChangeEmployeeTransaction {
    public ChangeNameTransaction(long empId, String name) {
        super(empId);
        setName(name);
    }

    String name;
    public ChangeNameTransaction setName(String name) {
        this.name = name;
        return this;
    }

    public void update(Employee employee) {
        employee.setName(name);
    }
}
