package com.bjsxt.service.impl;

import com.bjsxt.service.TbItemParamService;
import com.ego.commons.pojo.EasyUIDatagrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.pojo.TbItemParamChild;
import com.ego.commons.utils.IDUtils;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.pojo.TbItemParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TbItemParamServiceImpl implements TbItemParamService {
    @Reference
    private TbItemParamDubboService tbItemParamDubboService;
    @Reference
    private TbItemCatDubboService tbItemCatDubboService;

    @Override
    public EasyUIDatagrid showItemParam(int page, int rows) {
        List<TbItemParam> list = tbItemParamDubboService.selectByPage(page, rows);
        List<TbItemParamChild> childList = new ArrayList<>();
        for (TbItemParam tbItemParam : list) {
            TbItemParamChild tbItemParamChild = new TbItemParamChild();
            BeanUtils.copyProperties(tbItemParam, tbItemParamChild);
            tbItemParamChild.setItemCatName(tbItemCatDubboService.selectById(tbItemParam.getItemCatId()).get(0).getName());
            childList.add(tbItemParamChild);
        }
        Long total = tbItemParamDubboService.selectCount();
        return new EasyUIDatagrid(childList, total);
    }

    @Override
    public EgoResult showItemParamByCatid(Long id) {
        TbItemParam tbItemParam = tbItemParamDubboService.selectByCatId(id);
        if (tbItemParam != null) {
            return EgoResult.ok(tbItemParam);
        }
        return null;
    }

    @Override
    public EgoResult insert(TbItemParam itemParam) {
        long id = IDUtils.genItemId();
        Date date = new Date();
        itemParam.setCreated(date);
        itemParam.setId(id);
        int index = tbItemParamDubboService.insert(itemParam);
        if (index == 1) {
            return EgoResult.ok();
        }
        return EgoResult.error("插入错误");
    }

    @Override
    public EgoResult delete(Long[] ids) {
        int index = tbItemParamDubboService.delete(ids);
        if (index == 1){
            EgoResult.ok();
        }
        return EgoResult.error("批量删除失败");
    }
}
