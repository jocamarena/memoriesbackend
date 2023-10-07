package com.example.memoriesbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class MemoriesbackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemoriesbackendApplication.class, args);
    }

}
