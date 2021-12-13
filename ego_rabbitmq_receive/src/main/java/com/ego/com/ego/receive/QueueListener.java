package com.ego.com.ego.receive;

import com.ego.commons.utils.HttpClientUtil;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class QueueListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name="${ego.rabbitmq.content.queueName}"),
            exchange = @Exchange("amq.direct")
    ))
    public void content(Object o) {
        HttpClientUtil.doGet("http://localhost:8082/bigad");
    }
}
