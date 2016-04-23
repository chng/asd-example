package me.service;

import me.db.query.GpayrollDataBase;

/**
 * Created by chn on 16/4/23.
 */
public class AddAffiliationMemberTransaction implements Transaction {

    Employee employee;
    Affiliation affiliation;

    public AddAffiliationMemberTransaction(Employee emp, Affiliation aff) {
        this.employee = emp;
        this.affiliation = aff;
    }

    public void execute() {
        employee.getAffiliations().add(affiliation);
        affiliation.getMembers().add(employee);
        GpayrollDataBase.saveAffiliation(affiliation);
        GpayrollDataBase.saveEmployee(employee);
    }
}
