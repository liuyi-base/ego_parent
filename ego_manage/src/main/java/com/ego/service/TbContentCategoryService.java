package com.ego.service;

import com.ego.commons.pojo.EasyUITree;

import java.util.List;

public interface TbContentCategoryService {
    List<EasyUITree> showContentCategory(Long pid);
}
