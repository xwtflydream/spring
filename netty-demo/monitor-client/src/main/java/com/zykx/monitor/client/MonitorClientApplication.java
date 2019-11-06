package com.zykx.monitor.client;

import com.zykx.monitor.client.netty.NettyClient;
import io.netty.bootstrap.Bootstrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MonitorClientApplication {

    public static void main(String[] args) {
//        SpringApplication.run(MonitorClientApplication.class, args);
        NettyClient client = new NettyClient(new Bootstrap());
        client.connect();
    }

}
