package me.sml.springaop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TestAspect {

    @Before("execution(* me.sml.springaop.service.*.*AOP(..))")
    public void onBeforeHandler(JoinPoint joinPoint){
        log.info("============= onBeforeHandler ==============");
    }

    @After("execution(* me.sml.springaop.service.*.*AOP(..))")
    public void onAfterHandler(JoinPoint joinPoint){
        log.info("============= onAfterHandler ===============");
    }

    /**
     * @author SML
     * @Before @After를 혼합한 것과 동일
     * */
//    @Around("execution(* me.sml.springaop.service.*.*AOP(..))")
//    public void onAroundHandler(JoinPoint joinPoint){
//        log.info("============= onAroundHandler ===============");
//    }

    @AfterReturning(pointcut = "execution(* me.sml.springaop.service.*.*AOP(..))",
                    returning = "str")
    public void onAfterReturningHandler(JoinPoint joinPoint, Object str){
        log.info("============= onAfterReturningHandler ===============");
        log.info("@AfterReturning : {}", str);
    }

    @Pointcut("execution(* me.sml.springaop.service.*.*AOP(..))")
    public void onPointCut(JoinPoint joinPoint){
        log.info("============= onPointCut ===============");
    }

}
