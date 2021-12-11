package com.ego.dubbo.service.impl;

import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.mapper.TbItemDescMapper;
import com.ego.pojo.TbItemDesc;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class TbItemDescDubboServiceImpl implements TbItemDescDubboService {
    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Override
    public TbItemDesc selectById(Long id) {
        return tbItemDescMapper.selectByPrimaryKey(id);
    }
}
