package com.ego.service;

import com.ego.commons.pojo.EasyUIDatagrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbItemParam;

public interface TbItemParamService {
    EasyUIDatagrid showItemParam(int page, int rows);

    EgoResult showItemParamByCatid(Long id);

    EgoResult insert(TbItemParam itemParam);

    EgoResult delete(Long[] ids);
}
