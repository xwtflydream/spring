package com.zykx.monitor.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zykx.monitor.manager.mybatis")
public class MonitorMangerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorMangerApplication.class, args);
    }

}
