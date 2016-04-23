package me.service;

import me.BaseTest;
import me.db.query.GpayrollDataBase;
import org.junit.Test;

/**
 * Created by chn on 16/4/23.
 */
public class AddAffiliationTest extends BaseTest {

    @Test
    public void test() {

        // 添加一个协会
        long affId = 1024;
        String name = "Football Pie";
        Transaction t = new AddAffiliationTransaction( affId, name);
        t.execute();

        Affiliation affiliation = GpayrollDataBase.getAffiliationByAffId(affId);
        assert(affiliation.getAffId()==affId);
        assert(affiliation.getName().equals(name));
    }
}
