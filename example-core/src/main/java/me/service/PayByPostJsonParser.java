package me.service;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by chn on 16/4/22.
 */
@Deprecated
@Component
public class PayByPostJsonParser extends JsonToObjectParser {
    @Resource
    JsonParserChain payByJsonParser;

    public PayByPostJsonParser() {
        super(PayByPost.class);
    }
    @PostConstruct
    public void init() {
        payByJsonParser.add(this);
    }

}
