package com.bjsxt.service;

import com.ego.commons.pojo.EasyUIDatagrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemParamItem;

public interface TbItemService {

    EasyUIDatagrid showItem(int page, int rows);

    EgoResult updateStatus(long[] ids,int status);

    EgoResult update(TbItem item,String desc,String itemParams,long itemParamId);

    EgoResult insert(TbItem item, String desc, String itemParams);
}
