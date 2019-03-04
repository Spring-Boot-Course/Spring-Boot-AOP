package me.sml.springaop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
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

    @Around("execution(* me.sml.springaop.service.*.*AOP(..))")
    public Object onAroundHandler(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("============= onAroundHandler ===============");
        log.info("============= aroundAdvice() : before ===============");
        Object result = proceedingJoinPoint.proceed();
        log.info("============= aroundAdvice() : after ===============");
        return result;
    }

    @AfterReturning(pointcut = "execution(* me.sml.springaop.service.*.*AOP(..))",
                    returning = "str")
    public void onAfterReturningHandler(JoinPoint joinPoint, Object str){
        log.info("============= onAfterReturningHandler ===============");
        log.info("@AfterReturning : {}", str);
    }

    @AfterThrowing(pointcut = "execution(* me.sml.springaop.service.*.*AOP(..))",
            throwing = "ex")
    public void onAfterThrowingHandler(Throwable ex){
        log.info("============= onAfterThrowingHandler ===============");
    }


}
