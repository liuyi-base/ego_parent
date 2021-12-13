package com.ego.service.impl;

import com.ego.service.TbItemCatService;
import com.ego.commons.pojo.EasyUITree;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.pojo.TbItemCat;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TbItemCatServiceImpl implements TbItemCatService {
    @Reference
    public TbItemCatDubboService tbItemCatDubboService;

    @Override
    public List<EasyUITree> showTree(Long pid) {
        List<EasyUITree> easyUITreeList = new ArrayList<>();
        List<TbItemCat> list = tbItemCatDubboService.selectById(pid);
        for (TbItemCat tbItemCat : list) {
            EasyUITree easyUITree = new EasyUITree();
            easyUITree.setId(tbItemCat.getId());
            easyUITree.setText(tbItemCat.getName());
            easyUITree.setState(tbItemCat.getIsParent() ? "closed" : "open");
            easyUITreeList.add(easyUITree);
        }
        return easyUITreeList;
    }
}
