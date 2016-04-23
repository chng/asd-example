package me.service;

import com.google.common.collect.Lists;
import me.util.JsonUtil;

import java.util.List;

/**
 * Created by chn on 16/4/22.
 */
//@Component
public class JsonParserChain extends JsonToObjectParser {

    List<JsonToObjectParser> parsers = Lists.newLinkedList();

    public JsonParserChain() {
        super(Object.class);
    }

    public void add(JsonToObjectParser parser) {
        this.parsers.add(parser);
    }

    public Object parse(String jsonString) {
        for(JsonToObjectParser parser: parsers) {
            Object o = parser.parse(jsonString);
            if(o!=null) {
                return o;
            }
        }
        return null;
    }

    public String toString() {
        return JsonUtil.toJSONString(parsers);
    }
}
