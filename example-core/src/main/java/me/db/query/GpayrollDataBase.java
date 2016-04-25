package me.db.query;

import com.avaje.ebean.Ebean;
import me.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import javax.persistence.PersistenceException;
import java.util.Date;
import java.util.List;

/**
 * Created by chn on 16/1/23.
 *
 * 此类包含许多持久层的操作, 需要重构成多个类, 将职责拆分掉.
 */
@Component
public class GpayrollDataBase {

    static Logger log = LoggerFactory.getLogger(GpayrollDataBase.class);

    static JsonParserChain payClassificationJsonParser;
    static JsonParserChain payByJsonParser;
    static JsonParserChain payScheduleJsonParser;

    @Resource
    public void setPayByJsonParser(JsonParserChain payByJsonParser) {
        GpayrollDataBase.payByJsonParser = payByJsonParser;
    }
    @Resource
    public void setPayScheduleJsonParser(JsonParserChain payScheduleJsonParser) {
        GpayrollDataBase.payScheduleJsonParser = payScheduleJsonParser;
    }
    @Resource
    public void setPayClassificationJsonParser(JsonParserChain payClassificationJsonParser) {
        GpayrollDataBase.payClassificationJsonParser = payClassificationJsonParser;
    }

    static void parseStorableInEmployee(Employee e) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        if(e==null) {
            return;
        }

        e.setPayBy((PayBy) SerializationUtils.deserialize(e.getPayByJson()));
        e.setPayClassification((PayClassification) SerializationUtils.deserialize(e.getPayClassificationJson()));

//        if(!Strings.isEmpty(e.getPayByJson())) {
//            log.info(e.getPayByJson());
//            e.setPayBy((PayBy) payByJsonParser.parse(e.getPayByJson()));
//        }
//        if(!Strings.isEmpty(e.getPayClassificationJson())) {
//            log.info(e.getPayClassificationJson());
//            e.setPayClassification((PayClassification) payClassificationJsonParser.parse(e.getPayClassificationJson()));
//        }


    }

    static void parseEmployeeAttrToStorable(Employee e) {
//        e.setPayByJson(JsonUtil.toJSONString(e.getPayBy()));
//        e.setPayClassificationJson(JsonUtil.toJSONString(e.getPayClassification()));
        e.setPayByJson(SerializationUtils.serialize(e.getPayBy()));
        e.setPayClassificationJson(SerializationUtils.serialize(e.getPayClassification()));
    }

    public static Employee getEmployeeById(long empId) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Employee e = Ebean.find(Employee.class, empId);
        parseStorableInEmployee(e);
        return e;
    }

    public static void addEmployee(Employee employee) {
        parseEmployeeAttrToStorable(employee);
        try {
            Ebean.insert(employee);
        } catch (PersistenceException e) {
            log.error(e.getMessage(),e);
        }
    }

    public static void saveEmployee(Employee employee) {
        parseEmployeeAttrToStorable(employee);
        try {
            Ebean.save(employee);
        } catch (PersistenceException e) {
            log.error(e.getMessage(),e);
        }
    }

    public static void addTimeCard(TimeCard timeCard) {
        try {
            Ebean.save(timeCard);
        } catch (PersistenceException e) {
            log.error(e.getMessage(),e);
        }
    }

    public static List<TimeCard> getTimeCardByEmpId(long empId, Date startTime, Date endTime) {
        return Ebean.find(TimeCard.class).where()
                .eq("empId", empId)
                .ge("startTime", startTime)
                .lt("endTime", endTime)
                .findList();
    }

    public static void addSalesReceipt(SalesReceipt salesReceipt) {
        Ebean.insert(salesReceipt);
    }

    public static List<SalesReceipt> getSalesReceipt(long empId, Date startTime, Date endTime) {
        return Ebean.find(SalesReceipt.class).where()
                .eq("emp_id", empId)
                .ge("date", startTime)
                .lt("date", endTime)
                .findList();
    }

    public static void addAffiliation(Affiliation affiliation) {
        try {
            Ebean.insert(affiliation);
        } catch (PersistenceException e) {
            log.error(e.getMessage(),e);
        }
    }

    public static void saveAffiliation(Affiliation aff) {
        try {
            Ebean.save(aff);
        } catch (PersistenceException e) {
            log.error(e.getMessage(),e);
        }
    }

    public static Affiliation getAffiliationByAffId(long affId) {
        return Ebean.find(Affiliation.class, affId);
    }

    public static Affiliation getAffiliationByAffName(String name) {
        return Ebean.find(Affiliation.class).where().eq("name", name).findUnique();
    }

    public static List<Object> getAllEmpIds() {
        List<Object> ids = Ebean.find(Employee.class).findIds();
        return ids;
    }

    public static List<Employee> getAllEmployeesByPage(long startId, long endId) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        //List<Employee> emps = Ebean.find(Employee.class).findPagedList(0, pageSize).getList();
        List<Employee> emps = Ebean.find(Employee.class)
                .where()
                .ge("empId", startId).lt("empId", endId)
                .findList();
        for(Employee e: emps) {
            parseStorableInEmployee(e);
        }
        return emps;
    }
}
