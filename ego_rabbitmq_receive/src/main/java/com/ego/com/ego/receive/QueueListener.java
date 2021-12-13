package com.ego.com.ego.receive;

import com.ego.commons.utils.HttpClientUtil;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class QueueListener {
    @Value("${ego.search.insert}")
    private String searchInsertUrl;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "${ego.rabbitmq.content.queueName}"),
            exchange = @Exchange("amq.direct")
    ))
    public void content(Object o) {
        HttpClientUtil.doGet("http://localhost:8082/bigad");
    }

    @RabbitListener(bindings = {@QueueBinding(value = @Queue(name = "${ego.rabbitmq.item.insertName}"), exchange = @Exchange(name = "amq.direct"))})
    public void itemInsert(String id) {
        Map<String, String> param = new HashMap<>();
        param.put("id", id + "");
        String result = HttpClientUtil.doPost(searchInsertUrl, param);
    }
}
