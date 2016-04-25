package me.db.query;

import me.BaseTest;
import me.service.AddSalariedEmployee;
import me.service.Employee;
import me.service.Transaction;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

/**
 * Created by chn on 16/4/25.
 */
public class GpayrollDataBaseTest extends BaseTest{


    @BeforeClass
    public static void init () {
        Transaction t;
        int N = 100;
        for(int i=1; i<=N; i++) {
            t = new AddSalariedEmployee(i, "AnomEmployee", "No Addr", 1000.);
            t.execute();
        }
    }

    @Test
    public void testGetAllEmpId() {

        List<Object> allEmpIds = GpayrollDataBase.getAllEmpIds();
        assert(allEmpIds.size()==100);
        for(int i=0; i<100; ++i){
            assert(i+1 == (Long)allEmpIds.get(i));
        }
    }

    @Test
    public void testGetAllEmployeeByPage() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        List<Employee> employees = GpayrollDataBase.getAllEmployeesByPage(1, 21);
        System.out.println(employees.size());
        assert(employees.size()==20);
        for(int i=0; i<employees.size(); ++i){
            assert(employees.get(i).getEmpId()==i+1);
        }
    }

}
