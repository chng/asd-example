package me.service;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by chn on 16/4/22.
 */
@Deprecated
@Component
public class SalariedSalaryJsonParser extends JsonToObjectParser {
    @Resource
    JsonParserChain payClassificationJsonParser;

    public SalariedSalaryJsonParser() {
        super(SalariedSalary.class);
    }
    @PostConstruct
    public void init() {
        payClassificationJsonParser.add(this);
    }

}
