package com.zykx.monitor.server.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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

    @Value("${mq.topic.rout.key}")
    private String routKey;

    @Value("${mq.topic.queue.name}")
    private String queueName;

    @Bean
    public Queue queueMessage() {
        return new Queue(queueName);
    }

    //创建交换机topicExchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding bindingExchange() {
        return BindingBuilder.bind(queueMessage()).to(exchange()).with(routKey);
    }
}
