package com.zykx.monitor.manager.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 通过@RabbitListener对hello队列进行监听
 */
@Component
public class Receiver {

//    @RabbitListener(queues = "queue1")
//    public void receiver1(String message) {
//        System.out.println("queue1: [" + message + "]");
//    }
//
//    @RabbitListener(queues = "queue2")
//    public void receiver2(String message) {
//        System.out.println("queue2: [" + message + "]");
//    }
//
//    @RabbitListener(queues = "queue3")
//    public void receiver3(String message) {
//        System.out.println("queue3: [" + message + "]");
//    }
}
