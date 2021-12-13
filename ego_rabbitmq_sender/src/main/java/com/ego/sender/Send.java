package com.ego.sender;



import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Send {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String queue, Object o) {
        rabbitTemplate.convertAndSend(queue, o);
    }
}
