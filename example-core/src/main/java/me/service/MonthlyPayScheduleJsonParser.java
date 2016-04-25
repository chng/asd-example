package me.service;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by chn on 16/4/22.
 */
@Deprecated
@Component
public class MonthlyPayScheduleJsonParser extends JsonToObjectParser {

    @Resource
    JsonParserChain payScheduleJsonParser;

    public MonthlyPayScheduleJsonParser() {
        super(MonthlyPaySchedule.class);
    }
    @PostConstruct
    public void init() {
        payScheduleJsonParser.add(this);
    }

}
