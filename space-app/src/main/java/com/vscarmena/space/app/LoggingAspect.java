package com.vscarmena.space.app;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution (* com.vscarmena.acme.*.*(..))")
    public void log(JoinPoint joinPoint) {
        LOGGER.info(buildMethodToPrint(joinPoint));
    }

    private String buildMethodToPrint(JoinPoint joinPoint) {
        return "Call to -> " + joinPoint.getSignature() + " with params [" +
                StringUtils.arrayToCommaDelimitedString(joinPoint.getArgs())+ "]";
    }
}
