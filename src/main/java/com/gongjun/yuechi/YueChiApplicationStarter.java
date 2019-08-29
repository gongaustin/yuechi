package com.gongjun.yuechi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {"com.gongjun.yuechi"})
@MapperScan(basePackages = {"com.gongjun.yuechi.mapper"})
@EnableAutoConfiguration
@EnableSwagger2
public class YueChiApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(YueChiApplicationStarter.class, args);
    }

}

