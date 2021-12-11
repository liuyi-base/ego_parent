package com.ego.dubbo.service;

import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;

import java.util.List;

public interface TbItemDescDubboService {
    TbItemDesc selectById(Long id);
}
