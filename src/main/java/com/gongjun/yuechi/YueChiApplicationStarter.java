package com.gongjun.yuechi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.gongjun.yuechi"})
@MapperScan(basePackages = {"com.gongjun.yuechi.mapper"})
@EnableAutoConfiguration
public class YueChiApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(YueChiApplicationStarter.class, args);
    }

}

