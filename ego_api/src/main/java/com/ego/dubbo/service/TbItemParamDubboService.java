package com.ego.dubbo.service;

import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParam;
import com.ego.pojo.TbItemParamItem;

import java.util.List;

public interface TbItemParamDubboService {

    List<TbItemParam> selectByPage(int pageNum, int pageSize);

    Long selectCount();

    TbItemParam selectByCatId(Long id);

    int insert(TbItemParam tbItemParam);

    int delete(Long[] ids);

    int insert(TbItem tbItem, TbItemDesc tbItemDesc, TbItemParamItem tbItemParamItem);
}
