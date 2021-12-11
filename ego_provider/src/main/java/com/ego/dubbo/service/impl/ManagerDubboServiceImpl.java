package com.ego.dubbo.service.impl;

import com.ego.dubbo.service.ManagerDubboService;
import com.ego.mapper.ManagerMapper;
import com.ego.pojo.Manager;
import com.ego.pojo.ManagerExample;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

// Provider 方 导入apache的@Service
@Service
public class ManagerDubboServiceImpl implements ManagerDubboService {
    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public Manager selectManagerByUserName(String name) {
        ManagerExample managerExample = new ManagerExample();
        managerExample.createCriteria().andUsernameEqualTo(name);
        List<Manager> managerList = managerMapper.selectByExample(managerExample);
        if (managerList != null && managerList.size() != 0) {
            return managerList.get(0);
        }
        return null;
    }
}