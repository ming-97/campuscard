package com.pctc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan(basePackages={"com.pctc.mapper"})
//@ComponentScan("com.pctc")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

