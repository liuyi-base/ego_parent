package com.ego.service.impl;

import com.ego.sender.Send;
import com.ego.service.TbItemService;
import com.ego.commons.pojo.EasyUIDatagrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.IDUtils;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParamItem;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class TbItemServiceImpl implements TbItemService {

    @Reference
    private TbItemDubboService tbItemDubboService;
    @Autowired
    private Send send;
    @Value("${ego.rabbitmq.item.insertName}")
    private String itemInsert;

    @Override
    public EasyUIDatagrid showItem(int page, int rows) {
        List<TbItem> tbItemList = tbItemDubboService.selectByPage(page, rows);
        long total = tbItemList.size();
        return new EasyUIDatagrid(tbItemList, total);
    }

    @Override
    public EgoResult updateStatus(long[] ids, int status) {
        try {
            int result = tbItemDubboService.updateStatusByIds(ids, status);
            if (result == 1) {
                return EgoResult.ok();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return EgoResult.error("批量修改失败");
    }

    @Override
    public EgoResult update(TbItem item, String desc, String itemParams, long itemParamId) {
        Date date = new Date();
        Long id = IDUtils.genItemId();
        item.setUpdated(date);
        item.setId(id);
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setItemId(id);
        tbItemDesc.setUpdated(date);
        tbItemDesc.setCreated(date);

        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setId(itemParamId);
        tbItemParamItem.setParamData(itemParams);
        tbItemParamItem.setUpdated(date);

        int index = tbItemDubboService.update(item, tbItemDesc,tbItemParamItem);
        if (index == 1) {
            send.send(itemParams,itemInsert);
            return EgoResult.ok();
        } else {
            return EgoResult.error("新增失败");
        }
    }


    @Override
    public EgoResult insert(TbItem item, String desc, String itemParams) {
        Date date = new Date();
        Long id = IDUtils.genItemId();
        item.setId(id);
        item.setCreated(date);
        item.setUpdated(date);
        item.setStatus((byte) 1);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        tbItemDesc.setItemDesc(desc);

        // 商品规格参数
        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setId(IDUtils.genItemId());
        tbItemParamItem.setCreated(date);
        tbItemParamItem.setUpdated(date);
        tbItemParamItem.setItemId(id);
        tbItemParamItem.setParamData(itemParams);

        int index = tbItemDubboService.insert(item, tbItemDesc, tbItemParamItem);
        if (index == 1) {
            send.send(itemParams,itemInsert);
            return EgoResult.ok();
        }

        return EgoResult.error("新增失败");
    }


}
