package com.spring.library_management_system.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
@Aspect
public class LoggingAspect {

    private final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.spring.library_management_system.service.BookService.createBook(..)) || " +
            "execution(* com.spring.library_management_system.service.BookService.updateBook(..)) || " +
            "execution(* com.spring.library_management_system.service.PatronService.*(..))")
    public void serviceMethods() {}

    @Before("serviceMethods()")
    public void logMethodCall(JoinPoint jp) {
        LOGGER.info("Method Called: " + jp.getSignature().getName());
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "exception")
    public void logException(JoinPoint jp, Throwable exception) {
        LOGGER.error("Exception in: " + jp.getSignature().getName() + " Method and it's Error Message is: " + exception.getMessage());
    }
}
