package me.service;

import me.db.query.GpayrollDataBase;

/**
 * Created by chn on 16/4/24.
 */
public class DelAffiliationMemberTransaction extends ChangeAffiliationMemberTransaction {

    public DelAffiliationMemberTransaction(Employee emp, Affiliation aff) {
        super(emp, aff);
    }

    public void execute() {
        if(checkNotNull()) {
            employee.getAffiliations().remove(affiliation);
            affiliation.getMembers().remove(employee);
            GpayrollDataBase.saveAffiliation(affiliation);
            GpayrollDataBase.saveEmployee(employee);
        }

    }
}
