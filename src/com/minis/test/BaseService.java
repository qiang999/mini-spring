package com.minis.test;

import com.minis.beans.factory.annotation.Autowired;

/**
 * @Title: BaseService
 * @Package: com.minis.test
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/7 - 21:58
 */
public class BaseService {

    @Autowired
    private BaseBaseService bbs;

    public BaseBaseService getBbs() {
        return bbs;
    }

    public void setBbs(BaseBaseService bbs) {
        this.bbs = bbs;
    }

    public BaseService() { }

    public void sayHello() {
        System.out.println("Base Service says Hello"); bbs.sayHello();
    }
}
