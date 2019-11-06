package com.zykx.monitor.server;

import com.zykx.monitor.server.netty.NettyServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zykx.monitor.server.mybatis")
public class MonitorServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorServerApplication.class, args);

        // 启动netty服务
        NettyServer nettyServer = new NettyServer();
        nettyServer.run();
    }

}
