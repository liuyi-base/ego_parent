package com.ego.dubbo.service;

import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParamItem;

import java.util.List;

/*** 商品表数据库操作 */
public interface TbItemDubboService {
    /*** 分页查询
     * @param pageSize 每页大小
     * @param pageNumber 页码
     * @return 当前页显示数据
     */
    List<TbItem> selectByPage(int pageSize, int pageNumber);

    /***
     *查询总条数
     * @return 总条数
     */
    long selectCount();

    int updateStatusByIds(long[] ids,int status) throws RuntimeException;

    int insert(TbItem tbItem, TbItemDesc tbItemDesc, TbItemParamItem tbItemParamItem) ;

    int update(TbItem tbItem, TbItemDesc tbItemDesc, TbItemParamItem tbItemParamItem) throws RuntimeException;
}