package com.ego.dubbo.service;

import com.ego.pojo.TbItemCat;

import java.util.List;

public interface TbItemCatDubboService {

    List<TbItemCat> selectById(Long pid);
}
