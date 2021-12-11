package com.ego.dubbo.service.impl;

import com.ego.dubbo.service.TbItemDubboService;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.mapper.TbItemDescMapper;
import com.ego.mapper.TbItemMapper;
import com.ego.mapper.TbItemParamItemMapper;
import com.ego.mapper.TbItemParamMapper;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParam;
import com.ego.pojo.TbItemParamItem;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TbItemParamDubboServiceImpl implements TbItemParamDubboService {
    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Override
    public List<TbItemParam> selectByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(null);
        PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public Long selectCount() {
        return tbItemParamMapper.countByExample(null);
    }

    @Override
    public TbItemParam selectByCatId(Long id) {
        return tbItemParamMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(TbItemParam tbItemParam) {
        return tbItemParamMapper.insert(tbItemParam);
    }

    @Override
    @Transactional
    public int delete(Long[] ids) {
        int index = 0;
        for (Long id : ids) {
            index += tbItemParamMapper.deleteByPrimaryKey(id);
        }
        if (index == ids.length) {
            return 1;
        }
        throw new RuntimeException("批量删除失败");
    }

    @Override
    public int insert(TbItem tbItem, TbItemDesc tbItemDesc, TbItemParamItem tbItemParamItem) {
        int index1 = tbItemMapper.insert(tbItem);
        int index2 = tbItemDescMapper.insert(tbItemDesc);
        int index3 = tbItemParamItemMapper.insert(tbItemParamItem);
        if (index1 == 1 && index2 == 1 && index3 == 1) {
            return 1;
        }
        throw new RuntimeException("修改失败");
    }
}
