package com.lucky.boot;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@Slf4j
@ComponentScan(basePackages = {"com.lucky"})
@MapperScan(value = {"com.lucky.infrastructure.repository.mysql.mapper"})


public class LuckyApiserverBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuckyApiserverBootApplication.class, args);
        log.info("lucky-apiServer-boot-successful");
    }

}
