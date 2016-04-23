package me.service;

import me.db.query.GpayrollDataBase;

/**
 * Created by chn on 16/4/23.
 */
public class AddAffiliationMemberTransaction extends ChangeAffiliationMemberTransaction {


    public AddAffiliationMemberTransaction(Employee emp, Affiliation aff) {
        super(emp, aff);
    }

    public void execute() {
        if(checkNotNull()) {
            employee.getAffiliations().add(affiliation);
            affiliation.getMembers().add(employee);
            GpayrollDataBase.saveAffiliation(affiliation);
            GpayrollDataBase.saveEmployee(employee);
        }
    }
}
