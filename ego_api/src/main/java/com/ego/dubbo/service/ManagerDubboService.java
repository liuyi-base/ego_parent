package com.ego.dubbo.service;

import com.ego.pojo.Manager;

public interface ManagerDubboService {

    Manager selectManagerByUserName(String name);

}
