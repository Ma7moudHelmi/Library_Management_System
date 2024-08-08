package com.spring.library_management_system.Aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceMonitorAspect {
    private final Logger LOGGER = LoggerFactory.getLogger(PerformanceMonitorAspect.class);

    @Pointcut("execution(* com.spring.library_management_system.service.BookService.createBook(..)) || " +
            "execution(* com.spring.library_management_system.service.BookService.updateBook(..)) || " +
            "execution(* com.spring.library_management_system.service.PatronService.*(..))")
    public void serviceMethods() {}

    @Around("serviceMethods()")
    public Object monitorTime(ProceedingJoinPoint jp) throws Throwable{
        long startTime = System.currentTimeMillis();
        Object obj = jp.proceed();
        long endTime = System.currentTimeMillis();
        LOGGER.info("Time taken by " + jp.getSignature().getName() + " method is: " +(endTime-startTime)+" ms");
        return obj;
    }
}
