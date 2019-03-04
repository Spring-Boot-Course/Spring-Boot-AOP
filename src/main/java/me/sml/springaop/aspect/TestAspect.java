package me.sml.springaop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TestAspect {

    @Before("execution(* me.sml.springaop.service.*.*Aop(..))")
    public void onBeforeHandler(JoinPoint joinPoint){
        log.info("============= onBeforeHandler ==============");
    }

    @After("execution(* me.sml.springaop.service.*.*Aop(..))")
    public void onAfterHandler(JoinPoint joinPoint){
        log.info("============= onAfterHandler ===============");
    }

    @AfterReturning(pointcut = "execution(* me.sml.springaop.service.*.*Aop(..))",
                    returning = "str")
    public void onAfterReturningHandler(){
        log.info("============= onAfterReturningHandler ===============");
        log.info("@AfterReturning : {}", str);
    }

    @Pointcut("execution(* me.sml.springaop.service.*.*Aop(..))")
    public void onPointCut(JoinPoint joinPoint){
        log.info("============= onPointCut ===============");
    }

}
