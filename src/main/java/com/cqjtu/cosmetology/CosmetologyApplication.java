package com.cqjtu.cosmetology;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cqjtu.cosmetology.dao")
public class CosmetologyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CosmetologyApplication.class, args);
    }

}
