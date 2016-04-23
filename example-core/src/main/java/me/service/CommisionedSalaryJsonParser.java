package me.service;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by chn on 16/4/22.
 */
@Component
public class CommisionedSalaryJsonParser extends JsonToObjectParser {

    @Resource
    JsonParserChain payClassificationJsonParser;

    public CommisionedSalaryJsonParser() {
        super(CommisionedSalary.class);
    }
    @PostConstruct
    public void init() {
        payClassificationJsonParser.add(this);
    }
}
