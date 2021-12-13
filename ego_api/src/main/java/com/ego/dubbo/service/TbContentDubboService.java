package com.ego.dubbo.service;

import com.ego.commons.exception.DaoException;
import com.ego.pojo.TbContent;

import java.util.List;

public interface TbContentDubboService {
    /**
     * 不同分类下的模糊查询
     * @param categoryId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<TbContent> selectBypage(Long categoryId, int pageNumber, int pageSize);

    /**
     * 一定是根据类目查询数量。
     * @param categoryId
     * @return
     */
    long selectCountByCategoryid(Long categoryId);

    /**
     * 新增
     * @param tbContent
     * @return
     */
    int insert(TbContent tbContent);

    /**
     * 修改
     * @param tbContent
     * @return
     */
    int update(TbContent tbContent);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteByIds(long[] ids) throws DaoException;

    List<TbContent> selectAllByCategoryid(Long categoryId);
}
