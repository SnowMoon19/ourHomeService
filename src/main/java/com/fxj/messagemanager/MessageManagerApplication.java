package com.fxj.messagemanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 开启Mapper扫描，指定包
@MapperScan("com.fxj.usermanager.mapper")
// 允许事务操作
@EnableTransactionManagement
@SpringBootApplication
public class MessageManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageManagerApplication.class, args);
    }

}
