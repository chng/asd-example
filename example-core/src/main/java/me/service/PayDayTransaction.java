package me.service;

import com.google.common.collect.Maps;
import me.db.query.GpayrollDataBase;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by chn on 16/4/24.
 */
public class PayDayTransaction implements Transaction {
    private Date payDay;
    private Map<Long, PayCheck> payChecks = Maps.newHashMap();

    public PayDayTransaction(Date paydate) {
        payDay = paydate;
    }

    public void execute() {
        // 对每个empId, 检查是否到了PayDay
        //List<Long> empIds = GpayrollDataBase.getAllEmpIds();
        long startId = 1;
        long numsId = 20;
        try {
            for(List<Employee> employees = GpayrollDataBase.getAllEmployeesByPage(startId, startId+numsId);
                employees.isEmpty()==false;
                employees = GpayrollDataBase.getAllEmployeesByPage(startId, startId+numsId)) {

                for(Employee employee: employees) {
                    if(employee.isPayDay(payDay)) {
                        double grossPay = employee.getPayClassification().calcSalary(payDay);
                        PayCheck pc = new PayCheck(payDay, grossPay);
                        payChecks.put(employee.getEmpId(), pc);
                        employee.payDay(pc);
                    }
                }
                startId = employees.get(employees.size()-1).getEmpId()+1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PayCheck getPayCheck(long empId) {
        return payChecks.get(empId);
    }
}
