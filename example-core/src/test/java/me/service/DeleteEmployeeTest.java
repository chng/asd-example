package me.service;

import me.BaseTest;
import me.db.query.GpayrollDataBase;
import org.junit.Test;

/**
 * Created by chn on 16/2/1.
 */
public class DeleteEmployeeTest extends BaseTest {

    @Test
    public void test() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        long empId = 4L;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Joan", "", 2000.0);
        t.execute();
        Employee e = GpayrollDataBase.getEmployeeById(empId);
        assert(e!=null);
        DeleteEmployeeTransaction dt = new DeleteEmployeeTransaction(empId);
        dt.execute();
        e = GpayrollDataBase.getEmployeeById(empId);
        assert(e==null);
    }
}
