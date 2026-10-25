package com.example.demo.component;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerTimingAspect {
  private static final Logger log = LoggerFactory.getLogger(ControllerTimingAspect.class);

  @Around("execution(* com.example.demo.controllers..*(..))")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    long startTime = System.currentTimeMillis();
    Object result = joinPoint.proceed();
    log.info("result: {}", result);
    long endTime = System.currentTimeMillis();
    log.info("Time taken to process the request: {} ms", (endTime - startTime));
    return result;
  }
}
