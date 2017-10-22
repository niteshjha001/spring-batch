package com.nitesh.springbatchdemo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Configuration
@ImportResource(value = "classpath:integration-context.xml")
public class SpringbatchdemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringbatchdemoApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }



}
