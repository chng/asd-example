package me.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import me.service.Employee;
import me.service.HourlySalary;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * Created by chn on 16/4/22.
 */
public class JsonUtilTest {

    @Test
    public void testParse() {

//        String jsonString = "{\"salaryPerHour\":200}";
//        try {
//            JsonElement jsonElement = new JsonParser().parse(jsonString);
//            System.out.println(jsonElement);
//            System.out.println("good json: " + jsonString);
//        } catch (JsonParseException e) {
//            System.out.println("bad json: " + jsonString);
//        }

        String jsonString = "{\"salaryPerHour\":200}";
        System.out.println(JsonUtil.canParse(jsonString, HourlySalary.class));
        Object o = JsonUtil.toJavaObject(
                jsonString, HourlySalary.class
                );
        System.out.println(JSON.toJSONString(o));
        assert(o!=null);

        // TODO canParse的逻辑需要考虑
        jsonString = "{\"salaryPerMonth\":1000}";
        System.out.println(JsonUtil.canParse(jsonString, HourlySalary.class));
        o = JsonUtil.toJavaObject(
                jsonString, HourlySalary.class
        );
        System.out.println(JSON.toJSONString(o));
        assert(o!=null);

    }


    class SubObject {
        public Map map = Maps.newHashMap();
        public int a;

        public String getB() {
            return "BBB";
        }
    }

    @Test
    public void test() {
        System.out.println(JsonUtil.canParse("{\"a\":0,\"map\":{}}", SubObject.class));
        System.out.println(JsonUtil.toJSONString(new SubObject()));
    }

    @Test
    public void testReflection() {
        Class clazz = Employee.class;
        Set<Field> declaredFields = Sets.newHashSet(clazz.getDeclaredFields());
        Set<Field> fields = Sets.newHashSet(clazz.getFields());
        Set<Method> declaredMethods = Sets.newHashSet(clazz.getDeclaredMethods());
        Set<Method> methods = Sets.newHashSet(clazz.getMethods());

        System.out.println("------------declaredFields------------");
        printMemberName(declaredFields);
        System.out.println("------------fields------------");
        printMemberName(fields);
        System.out.println("------------declaredMethods------------");
        printMemberName(declaredMethods);
        System.out.println("------------methods------------");
        printMemberName(methods);
    }

    void printMemberName(Set c) {
        long index = 0;
        for(Object m: c) {
            Member member = (Member)m;
            String name=member.getName();
            if(!name.startsWith("_")) {
                System.out.println(++index+'\t'+name);
            }
        }
    }
}
