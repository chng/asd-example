package me.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chn on 16/4/24.
 */
public abstract class ChangeAffiliationMemberTransaction implements Transaction {

    Logger log = LoggerFactory.getLogger(this.getClass());

    Employee employee;
    Affiliation affiliation;

    public ChangeAffiliationMemberTransaction(Employee emp, Affiliation aff) {
        this.employee = emp;
        this.affiliation = aff;
    }

    protected boolean checkNotNull() {
        if(employee==null) {
            log.error("Failed in "+this.getClass()+". Employee is null.");
            return false;
        }
        if(affiliation==null) {
            log.error("Failed in "+this.getClass()+". Affiliation is null.");
            return false;
        }
        return true;
    }
}
