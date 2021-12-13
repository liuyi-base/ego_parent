package com.ego.receive;

import com.ego.commons.pojo.TbItemDetails;
import com.ego.commons.utils.HttpClientUtil;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.pojo.TbItem;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class QueueListener {
    @Value("${ego.search.insert}")
    private String searchInsertUrl;

    @Autowired
    private RedisTemplate redisTemplate;

    @Reference
    private TbItemDubboService tbItemDubboService;

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

        String[] ids = id.split(",");
        for (String idArr : ids) {
            String key = "com.ego.item::details:" + idArr;
            TbItem tbItem = tbItemDubboService.selectById(Long.parseLong(idArr));
            TbItemDetails details = new TbItemDetails();
            details.setId(tbItem.getId());
            details.setPrice(tbItem.getPrice());
            details.setSellPoint(tbItem.getSellPoint());
            details.setTitle(tbItem.getTitle());
            String img = tbItem.getImage();
            details.setImages(img != null && !img.equals("") ? img.split(",") : new String[1]);
            redisTemplate.opsForValue().set(key, details);
        }
    }


}
