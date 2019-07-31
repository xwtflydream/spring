package com.xwt.springboot.rabbitmq.demo.rabbitmq.demo.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Sender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender " + context);
        this.rabbitTemplate.convertAndSend("Sender ： " + context);
    }
}
