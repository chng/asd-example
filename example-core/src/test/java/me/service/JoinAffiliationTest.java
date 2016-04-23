package me.service;

import me.BaseTest;
import me.db.query.GpayrollDataBase;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by chn on 16/4/23.
 */
public class JoinAffiliationTest extends BaseTest {

    static long empId = 7L;
    static long affId = 1024L;
    static String affName = "Football Pie";

    @BeforeClass
    public static void addEmployee() {
        Transaction t = new AddHourlyEmployee(empId, "Bob", "Home", 200.0);
        t.execute();
        t = new AddAffiliationTransaction(affId, affName);
        t.execute();
    }

    @Test
    public void test() throws IllegalAccessException, ClassNotFoundException, InstantiationException {

        Employee emp = GpayrollDataBase.getEmployeeById(empId);
        Affiliation aff = GpayrollDataBase.getAffiliationByAffName(affName);

        Transaction t = new AddAffiliationMemberTransaction(emp, aff);
        t.execute();

        assert(GpayrollDataBase.getAffiliationByAffName(affName).getMembers().contains(
                GpayrollDataBase.getEmployeeById(empId)));
        assert(GpayrollDataBase.getEmployeeById(empId).getAffiliations().contains(
                GpayrollDataBase.getAffiliationByAffName(affName)));
    }
}
