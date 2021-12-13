package com.ego.controller;

import com.ego.service.TbContentCategoryService;
import com.ego.commons.pojo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TbContentCategoryController {
    @Autowired
    private TbContentCategoryService tbContentCategoryService;

    /*** 显示内容分类 * @param id * @return */
    @RequestMapping("/content/category/list")
    public List<EasyUITree> showContentCategory(@RequestParam(defaultValue = "0") Long id) {
        return tbContentCategoryService.showContentCategory(id);
    }
}