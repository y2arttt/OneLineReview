package com.oneLineReview.oneLineReview.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;

import java.util.UUID;

@Slf4j
@Aspect
@Configuration
public class ExceptionLoggingAop {

    @Around("execution(* com.oneLineReview.oneLineReview.common.exception.GlobalExceptionHandler.*(..)) || " +
            "execution(* com.oneLineReview.oneLineReview.common.exception.CustomGlobalExceptionHandler.*(..))")
    public Object logException(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Exception exception = null;

        for (Object arg : args) { //arg가 예외라면
            if (arg instanceof Exception) {
                exception = (Exception) arg;
                break;
            }
        }
        if (exception != null) {
            UUID uuid = UUID.randomUUID();
            log.error("### [{}] {} in {}.{}() - {}", uuid,
                    exception.getClass().getSimpleName(),
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    exception.getMessage());


            for (Object arg : args) {
                if (arg instanceof Model) {
                    ((Model) arg).addAttribute("logId", uuid.toString());
                }
            }
        }
        return joinPoint.proceed();
    }
}
