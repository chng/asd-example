package me.service;

import me.BaseTest;
import me.db.query.GpayrollDataBase;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by chn on 16/2/1.
 */
public class DeleteEmployeeTest extends BaseTest {

    @BeforeClass
    public static void beforeClass() {
        Transaction t = new AddCommisionedEmployee(4L, "Bob", "Home", 1000.00);
        t.execute();
    }

    @Test
    public void test() throws IllegalAccessException, ClassNotFoundException, InstantiationException {

        long empId = 4L;
        Employee e = GpayrollDataBase.getEmployeeById(empId);
        assert(e!=null);
        DeleteEmployeeTransaction dt = new DeleteEmployeeTransaction(empId);
        dt.execute();
        e = GpayrollDataBase.getEmployeeById(empId);
        assert(e==null);
    }
}
