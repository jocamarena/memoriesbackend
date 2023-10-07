package com.example.memoriesbackend.config;

import com.example.memoriesbackend.records.ExceptionRecord;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectConfig {
    private final Logger logger = LoggerFactory.getLogger(AspectConfig.class);
    @AfterThrowing(pointcut = "execution(* com.example.memoriesbackend.*.*.*(..))", throwing = "exception")
    public ResponseEntity<ExceptionRecord> logException(Exception exception) {

        return ResponseEntity.badRequest().body(new ExceptionRecord(exception.getMessage(), exception.getClass().getName()));
    }
}
