package me.service;

import me.db.query.GpayrollDataBase;

/**
 * Created by chn on 16/4/23.
 */
public class AddAffiliationTransaction implements Transaction {

    Affiliation affiliation;

    public AddAffiliationTransaction(long affId, String name) {
        affiliation = new Affiliation();
        affiliation.setAffId(affId);
        affiliation.setName(name);
   }

    public void execute() {
        GpayrollDataBase.addAffiliation(affiliation);
    }
}
