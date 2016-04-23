package me.service;

import me.db.query.GpayrollDataBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chn on 16/4/22.
 */

public abstract class ChangeEmployeeTransaction implements Transaction {
    /**
     * 此类本应作为其他ChangeXXXTransaction类的父类, 目的是满足OCP,
     * 但是功能划分的粒度如果太细, 会导致小的类太多. 因此决定将不同功能写到成员函数而不是子类中.
     * 但若如是做,则不符合Transaction类的设计原则. 可见,Transaction强制其实现类做到指责单一.
     */

    Logger log = LoggerFactory.getLogger(this.getClass());

    long empId;

    public ChangeEmployeeTransaction(long empId) {
        this.empId = empId;
    }

    public abstract void update(Employee e);

    public final void execute() {
        Employee employee = null;
        try {
            employee = GpayrollDataBase.getEmployeeById(empId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        update(employee);
        GpayrollDataBase.saveEmployee(employee);
    }
}
