package com.zykx.monitor.manager.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    @Autowired
    AmqpTemplate rabbitmqTemplate;

    @Value("${mq.topic.exchange.name}")
    private String exchangeName;
    @Value("${mq.topic.rout.key}")
    private String routKey;

    /**
     * 发送消息
     */
    public void send(String message) {
        rabbitmqTemplate.convertAndSend(exchangeName, routKey, message);
    }
}
