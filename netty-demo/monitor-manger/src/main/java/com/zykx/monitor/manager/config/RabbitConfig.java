package com.zykx.monitor.manager.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置类
 */
@Configuration
public class RabbitConfig {

    @Value("${mq.topic.exchange.name}")
    private String exchangeName;

    //创建交换机topicExchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }
}
