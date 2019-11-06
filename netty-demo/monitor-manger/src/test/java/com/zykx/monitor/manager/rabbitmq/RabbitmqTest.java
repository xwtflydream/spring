package com.zykx.monitor.manager.rabbitmq;

import com.zykx.monitor.manager.MonitorMangerApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MonitorMangerApplication.class)
public class RabbitmqTest {

    @Autowired
    private Sender sender;

    @Test
    public void hello() {
        sender.send("大家好！");
    }
 }
