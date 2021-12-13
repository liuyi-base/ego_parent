package com.ego.sender.config;




import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
public class SenderConfig {

    @Value("${ego.rabbitmq.content.queueName}")
    private String contentQueue;

    @Bean
    public Queue queue(){
        return new Queue(contentQueue);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("amqp.direct");
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).withQueueName();
    }
}
