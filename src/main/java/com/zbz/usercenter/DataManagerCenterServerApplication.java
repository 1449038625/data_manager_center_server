package com.zbz.usercenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zbz.usercenter.mapper")
public class DataManagerCenterServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataManagerCenterServerApplication.class, args);
    }

}
