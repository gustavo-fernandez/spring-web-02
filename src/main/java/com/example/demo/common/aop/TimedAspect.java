package com.example.demo.common.aop;

import java.time.Duration;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TimedAspect {

  @Around("@annotation(com.example.demo.common.annotation.Timed)")
  public Object timed(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    LocalDateTime startTime = LocalDateTime.now();
    Object result = proceedingJoinPoint.proceed();
    LocalDateTime endTime = LocalDateTime.now();
    Duration duration = Duration.between(startTime, endTime);
    log.info("Tiempo transcurrido en el método [{}] fue {} segundos",
      proceedingJoinPoint.getSignature().getName(), duration.getSeconds());
    return result;
  }

}
