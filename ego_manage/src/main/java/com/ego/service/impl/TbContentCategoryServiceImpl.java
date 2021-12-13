package com.ego.service.impl;

import com.ego.service.TbContentCategoryService;
import com.ego.commons.pojo.EasyUITree;
import com.ego.dubbo.service.TbContentCategoryDubboService;
import com.ego.pojo.TbContentCategory;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {
    @Reference
    private TbContentCategoryDubboService tbContentCategoryDubboService;

    @Override
    public List<EasyUITree> showContentCategory(Long pid) {
        List<TbContentCategory> list = tbContentCategoryDubboService.selectByPid(pid);
        List<EasyUITree> listTree = new ArrayList<>();
        for (TbContentCategory category : list) {
            EasyUITree tree = new EasyUITree();
            tree.setId(category.getId());
            tree.setState(category.getIsParent() ? "closed" : "open");
            tree.setText(category.getName());
            listTree.add(tree);
        }
        return listTree;
    }
}