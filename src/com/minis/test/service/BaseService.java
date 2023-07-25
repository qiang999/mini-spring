package com.minis.test.service;

import com.minis.beans.factory.annotation.Autowired;
import com.minis.test.service.BaseBaseService;

/**
 * @Title: BaseService
 * @Package: com.minis.test
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/25 - 14:22
 */
public class BaseService {
    public BaseBaseService getBbs() {
        return bbs;
    }

    public void setBbs(BaseBaseService bbs) {
        this.bbs = bbs;
    }

    @Autowired
    private BaseBaseService bbs;

    public BaseService(){}

    public void sayHello(){
        System.out.println("Base Service says hello");
        bbs.sayHello();
    }
}
