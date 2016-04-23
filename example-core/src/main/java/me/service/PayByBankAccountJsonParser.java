package me.service;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by chn on 16/4/22.
 */
@Component
public class PayByBankAccountJsonParser extends JsonToObjectParser {

    @Resource
    JsonParserChain payByJsonParser;

    public PayByBankAccountJsonParser() {
        super(PayByBankAccount.class);

    }
    @PostConstruct
    public void init() {
        payByJsonParser.add(this);
    }
}
