package com.ego.sender.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SenderConfig {

    @Value("${ego.rabbitmq.content.queueName}")
    private String contentQueue;
    @Value("${ego.rabbitmq.item.insertName}")
    private String itemInsert;

    @Bean
    public Queue queue() {
        return new Queue(contentQueue);
    }

    @Bean
    public Queue queueItemInsert() {
        return new Queue(itemInsert);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("amqp.direct");
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).withQueueName();
    }

    @Bean
    public Binding binding2(Queue queueItemInsert, DirectExchange directExchange) {
        return BindingBuilder.bind(queueItemInsert).to(directExchange).withQueueName();
    }
}
