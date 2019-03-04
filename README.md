# Spring-Boot-AOP

### AOP란?

공통모듈(횡단관심사)을 작성하고 필요한 코드에 삽입하는 프로그래밍 기법.

### 언제 사용될까?

- 성능 검사
- 트랜잭션 처리
- 로깅
- 예외 반환
- 검증

실 예로, @Transactional, @Cache 등에 적용되고 있다고 함.

### 용어

1. PointCut
    
    어느 부분(where)에 횡단 관심 모듈을 삽입 할 것인가를 정의한다. 메서드에 횡단 관심 모듈을 삽입한다.
    
2. JoinPoint
    
    언제(when) 횡단 관심 모듈을 삽입 할 지 정의한다. Before, After, AfterReturning, AfterThrowing, Around 등이 존재한다.
    
3. Advice

    횡단 관심 모듈 그 자체(what)를 의미한다. 
    
### 사용법

Dependency

```java
dependencies {
    compile('org.springframework.boot:spring-boot-starter-aop')  
}
```

Application(Config)
```java
@EnableAspectJAutoProxy
@SpringBootApplication
public class Application {
	public static void main(String[] args) { SpringApplication.run(Application.class, args);}
}

```

Aspect

```java
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

```

Controller

```java
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

```

Service

```java
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

```

결과

```http request
http://localhost:8080/AOP
```

```java
============= onAroundHandler ===============
============= aroundAdvice() : before =======
============= onBeforeHandler ==============
Hello, Spring boot With AOP
============= aroundAdvice() : after ========
============= onAfterHandler ===============
============= onAfterReturningHandler =======
@AfterReturning : Hello, Spring boot With AOP
```

### 참고

- https://heowc.dev/2018/02/07/spring-boot-aop/
- https://victorydntmd.tistory.com/178
- https://jeong-pro.tistory.com/171