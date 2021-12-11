package com.bjsxt.service.impl;

import com.bjsxt.service.TbItemDescService;
import com.bjsxt.service.TbItemService;
import com.ego.commons.pojo.EgoResult;
import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.pojo.TbItemDesc;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;


@Service
public class TbItemDescServiceImpl implements TbItemDescService {
    @Reference
    private TbItemDescDubboService tbItemDescDubboService;

    @Override
    public EgoResult selectById(Long id) {
        TbItemDesc tbItemDesc = tbItemDescDubboService.selectById(id);
        if(tbItemDesc!=null){
            return EgoResult.ok();
        }else{
            return EgoResult.error("查询失败");
        }
    }
}
