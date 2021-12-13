package com.ego.dubbo.service.impl;

import com.ego.dubbo.service.TbItemDubboService;
import com.ego.mapper.TbItemDescMapper;
import com.ego.mapper.TbItemMapper;
import com.ego.mapper.TbItemParamItemMapper;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemExample;
import com.ego.pojo.TbItemParamItem;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;


@Service
public class TbItemDubboServiceImpl implements TbItemDubboService {
    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Override
    public List<TbItem> selectByPage(int pageSize, int pageNumber) {
        PageHelper.startPage(pageSize, pageNumber);
        List<TbItem> tbItemList = tbItemMapper.selectByExample(null);
        PageInfo<TbItem> pageInfo = new PageInfo<>(tbItemList);
        return pageInfo.getList();
    }

    @Override
    public long selectCount() {
        return tbItemMapper.countByExample(null);
    }

    @Override
    @Transactional
    public int updateStatusByIds(long[] ids, int status) throws RuntimeException {
        Date date = new Date();
        int index = 0;
        for (int i = 0; i < ids.length; i++) {
            TbItem tbItem = new TbItem();
            tbItem.setId(ids[i]);
            tbItem.setStatus((byte) status);
            tbItem.setUpdated(date);
            index += tbItemMapper.updateByPrimaryKey(tbItem);
        }
        if (index == ids.length) {
            return 1;
        }
        throw new RuntimeException("批量修改失败");
    }



    @Override
    @Transactional
    public int insert(TbItem tbItem, TbItemDesc tbItemDesc, TbItemParamItem tbItemParamItem) throws RuntimeException {
        int index = tbItemMapper.insert(tbItem);
        if (index == 1) {
            int index2 = tbItemDescMapper.insert(tbItemDesc);
            if (index2 == 1) {
                int index3 = tbItemParamItemMapper.insert(tbItemParamItem);
                if (index3 == 1) {
                    return 1;
                }
            }
        }
        return 0;
    }

    @Override
    public int update(TbItem tbItem, TbItemDesc tbItemDesc, TbItemParamItem tbItemParamItem) throws RuntimeException {
        int index = tbItemMapper.updateByPrimaryKey(tbItem);
        if (index == 1) {
            int index2 = tbItemDescMapper.updateByPrimaryKey(tbItemDesc);
            if (index2 == 1) {
                int index3 = tbItemParamItemMapper.updateByPrimaryKey(tbItemParamItem);
                if(index3 == 1){
                    return 1;
                }
            }
        }
        return 0;
    }

    @Override
    public TbItem selectById(Long id) {
        return tbItemMapper.selectByPrimaryKey(id);
    }
}
