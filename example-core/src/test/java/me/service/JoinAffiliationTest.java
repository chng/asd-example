package me.service;

import com.sun.tracing.dtrace.ProviderAttributes;
import me.BaseTest;
import me.db.query.GpayrollDataBase;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by chn on 16/4/23.
 */
public class JoinAffiliationTest extends BaseTest {

    static long empId = 7L;

    @BeforeClass
    public static void addEmployee() {
        Transaction t = new AddHourlyEmployee(empId, "Bob", "Home", 200.0);
        t.execute();
        t = new AddAffiliationTransaction(1024, "Football Pie");
        t.execute();
    }

    @Test
    public void test() throws IllegalAccessException, ClassNotFoundException, InstantiationException {

        Employee emp = GpayrollDataBase.getEmployeeById(empId);
        Affiliation aff = GpayrollDataBase.getAffiliationByAffName("Football Pie");

        Transaction t = new AddAffiliationMemberTransaction(emp, aff);
        t.execute();

        assert(GpayrollDataBase.getAffiliationByAffName("Football Pie").getMembers().contains(
                GpayrollDataBase.getEmployeeById(empId)));
        assert(GpayrollDataBase.getEmployeeById(empId).getAffiliations().contains(
                GpayrollDataBase.getAffiliationByAffName("Football Pie")));
    }
}
