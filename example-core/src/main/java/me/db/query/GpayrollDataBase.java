package me.db.query;

import com.avaje.ebean.Ebean;
import me.service.*;
import me.util.JsonUtil;
import me.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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

    static void parseJsonInEmployee(Employee e) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        if(e==null) {
            return;
        }

        if(!Strings.isEmpty(e.getPayByJson())) {
            log.info(e.getPayByJson());
            e.setPayBy((PayBy) payByJsonParser.parse(e.getPayByJson()));
        }
        if(!Strings.isEmpty(e.getPayClassificationJson())) {
            log.info(e.getPayClassificationJson());
            e.setPayClassification((PayClassification) payClassificationJsonParser.parse(e.getPayClassificationJson()));
        }
        if(!Strings.isEmpty(e.getPayScheduleJson())) {
            log.info(e.getPayScheduleJson());
            e.setPaySchedule((PaySchedule) payScheduleJsonParser.parse(e.getPayScheduleJson()));
        }
    }

    static void parseEmployeeAttrToJson(Employee e) {
        e.setPayClassificationJson(JsonUtil.toJSONString(e.getPayClassification()));
        e.setPayByJson(JsonUtil.toJSONString(e.getPayBy()));
        e.setPayScheduleJson(JsonUtil.toJSONString(e.getPaySchedule()));
    }

    public static Employee getEmployeeById(long empId) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Employee e = Ebean.find(Employee.class, empId);
        parseJsonInEmployee(e);
        return e;
    }

    public static void addEmployee(Employee employee) {
        parseEmployeeAttrToJson(employee);
        Ebean.insert(employee);
    }

    public static void saveEmployee(Employee employee) {
        parseEmployeeAttrToJson(employee);
        Ebean.save(employee);
    }

    public static void addTimeCard(TimeCard timeCard) {
        Ebean.insert(timeCard);
    }

    public static List<TimeCard> getTimeCardByEmpId(long empId, Date startTime, Date endTime) {
        return Ebean.find(TimeCard.class).where()
                .eq("emp_id", empId)
                .ge("start_time", startTime)
                .lt("end_time", endTime)
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
        Ebean.insert(affiliation);
    }

    public static void saveAffiliation(Affiliation aff) {
        Ebean.save(aff);
    }

    public static Affiliation getAffiliationByAffId(long affId) {
        return Ebean.find(Affiliation.class, affId);
    }

    public static Affiliation getAffiliationByAffName(String name) {
        return Ebean.find(Affiliation.class).where().eq("name", name).findUnique();
    }

}
