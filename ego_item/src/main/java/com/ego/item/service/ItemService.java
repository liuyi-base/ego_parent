package com.ego.item.service;

import com.ego.item.pojo.ItemCategoryNav;
import com.ego.item.pojo.TbItemDetails;

public interface ItemService {
    ItemCategoryNav showItemCat();

    TbItemDetails showItem(Long id);
}