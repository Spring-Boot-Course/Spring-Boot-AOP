package me.sml.springaop.controller;

import me.sml.springaop.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping(value = "/noAOP")
    public String noAOP(){
        return testService.test();
    }

    @GetMapping(value = "/AOP")
    public String AOP(){
        return testService.testAOP();
    }

}
