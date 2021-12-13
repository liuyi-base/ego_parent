package com.ego.service.impl;

import com.ego.commons.exception.DaoException;
import com.ego.commons.pojo.EasyUIDatagrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.IDUtils;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.pojo.TbContent;
import com.ego.service.TbContentService;
import com.ego.sender.Send;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TbContentServiceImpl implements TbContentService {
    @Value("${ego.rabbitmq.content.queueName}")
    private String contentQueue;
    @Value("${ego.bigad.categoryId}")
    private Long bigadId;
    @Reference
    private TbContentDubboService tbContentDubboService;
    @Autowired
    private Send send;

    @Override
    public EasyUIDatagrid showContent(Long categoryId, int page, int rows) {
        List<TbContent> list = tbContentDubboService.selectBypage(categoryId, page, rows);
        long total = tbContentDubboService.selectCountByCategoryid(categoryId);
        return new EasyUIDatagrid(list, total);
    }

    @Override
    public EgoResult insert(TbContent tbContent) {
        tbContent.setId(IDUtils.genItemId());
        Date date = new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        int index = tbContentDubboService.insert(tbContent);
        if (index == 1) {
            if (tbContent.getCategoryId().equals(bigadId)) {
                send.send(contentQueue, "async");
            }
            return EgoResult.ok();
        }
        return EgoResult.error("新增失败");
    }

    @Override
    public EgoResult update(TbContent tbContent) {
        tbContent.setUpdated(new Date());
        int index = tbContentDubboService.update(tbContent);
        if (index == 1) {
            if (tbContent.getCategoryId().equals(bigadId)) {
                send.send(contentQueue, "async");
            }
            return EgoResult.ok();
        }
        return EgoResult.error("修改失败");
    }

    @Override
    public EgoResult delete(long[] ids) {
        try {
            int index = tbContentDubboService.deleteByIds(ids);
            if (index == 1) {
                send.send(contentQueue, "async");
                return EgoResult.ok();
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return EgoResult.error("删除失败");
    }
}
