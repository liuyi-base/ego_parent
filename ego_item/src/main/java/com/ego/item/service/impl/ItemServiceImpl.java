package com.ego.item.service.impl;

import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.item.pojo.CategoryNode;
import com.ego.item.pojo.ItemCategoryNav;
import com.ego.item.service.ItemService;
import com.ego.pojo.TbItemCat;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Reference
    private TbItemCatDubboService tbItemCatDubboService;

    @Override
    @Cacheable(cacheNames = "com.ego.item", key = "'showItemCat'")
    public ItemCategoryNav showItemCat() {
        ItemCategoryNav itemCategoryNav = new ItemCategoryNav();
        itemCategoryNav.setData(getAllItemCat(0L));
        return itemCategoryNav;
    }

    private List<Object> getAllItemCat(Long parentId) {
        List<TbItemCat> list = tbItemCatDubboService.selectById(parentId);
        List<Object> listResult = new ArrayList<>();
        for (TbItemCat cat : list) {
            if (cat.getIsParent()) {
                CategoryNode node = new CategoryNode();
                node.setU("/products/" + cat.getId() + ".html");
                node.setN("<a href='/products/" + cat.getId() + ".html'>" + cat.getName() + "</a>");
                node.setI(getAllItemCat(cat.getId()));
                listResult.add(node);
            } else {
                listResult.add("/products/" + cat.getId() + ".html|" + cat.getName());
            }
        }
        return listResult;
    }
}
