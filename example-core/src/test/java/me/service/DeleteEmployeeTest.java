package me.service;

import me.BaseTest;
import me.db.query.GpayrollDataBase;
import org.junit.Test;

import java.util.List;

/**
 * Created by chn on 16/2/1.
 */
public class DeleteEmployeeTest extends BaseTest {

    @Test
    public void test() throws IllegalAccessException, ClassNotFoundException, InstantiationException {

        List<Object> allEmpIds = GpayrollDataBase.getAllEmpIds();
        for (Object empId: allEmpIds) {
            Employee e = GpayrollDataBase.getEmployeeById((Long)empId);
            assert(e!=null);
            DeleteEmployeeTransaction dt = new DeleteEmployeeTransaction((Long)empId);
            dt.execute();
            e = GpayrollDataBase.getEmployeeById((Long)empId);
            assert(e==null);
        }

    }
}
