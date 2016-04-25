package me.service;

import me.BaseTest;
import me.db.query.GpayrollDataBase;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by chn on 16/4/22.
 */
public class ChangeEmployeeTest extends BaseTest {

    static long empId = 4L;

    @BeforeClass
    public static void addEmployee() {
        Transaction t = new AddHourlyEmployee(empId, "Bob", "Home", 200.0);
        t.execute();
    }

    @Test
    public void testChangeName() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        String newName = "A New Name";
        Transaction changeName = new ChangeNameTransaction(empId, newName);
        changeName.execute();
        assert(GpayrollDataBase.getEmployeeById(empId).getName().equals(newName));
    }

    @Test
    public void testChangeAddress() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        String newAddress = "My New Address";
        Transaction change = new ChangeAddressTransaction(empId, newAddress);
        change.execute();
        assert(GpayrollDataBase.getEmployeeById(empId).getAddr().equals(newAddress));
    }

    @Test
    public void testChangePayClassification() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        PayClassification newPayClassification = new CommisionedSalary(empId, 2000, 0.8);
        assert(newPayClassification.getPaySchedule()!=null);
        Transaction change = new ChangePayClassificationTransaction(empId, newPayClassification);
        change.execute();

        PayClassification pc = GpayrollDataBase.getEmployeeById(empId).getPayClassification();
        assert(pc.getEmpId()==empId);
        assert(((CommisionedSalary)pc).getProfitFactor()==0.8);
        assert(((CommisionedSalary)pc).getSalaryPerMonth()==2000.);
    }

    @Test
    public void testChangePayBy() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        String bankAcount = "0987654321";
        PayBy newPayby = new PayByBankAccount(empId, bankAcount);
        Transaction change = new ChangePayByTransaction(empId, newPayby);
        change.execute();

        PayBy pb = GpayrollDataBase.getEmployeeById(empId).getPayBy();
        assert(pb.getEmpId()==empId);
        assert(((PayByBankAccount)pb).getBankAccount().equals(bankAcount));
    }

}
