package com.minis.test;

import com.minis.beans.factory.annotation.Autowired;

/**
 * @Title: BaseService
 * @Package: com.minis.test
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/14 - 15:13
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

    public void sayHello() {
        System.out.println("Base Service says Hello");
        bbs.sayHello();
    }
}
