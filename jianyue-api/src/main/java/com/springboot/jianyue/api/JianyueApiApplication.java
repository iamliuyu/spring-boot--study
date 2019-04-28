package com.springboot.jianyue.api;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan({"com.springboot.jianyue.api.mapper","com.springboot.jianyue.api.mapper_back"})
@EnableSwagger2Doc
@EnableScheduling
public class JianyueApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JianyueApiApplication.class, args);
    }

}
