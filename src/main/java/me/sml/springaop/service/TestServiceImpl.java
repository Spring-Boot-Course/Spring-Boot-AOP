package me.sml.springaop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestServiceImpl implements TestService{

    @Override
    public String test() {
        String msg = "Hello, Spring boot With No AOP";
        log.info(msg);
        return msg;
    }

    @Override
    public String testAOP() {
        String msg = "Hello, Spring boot With AOP";
        log.info(msg);
        return msg;
    }
}
