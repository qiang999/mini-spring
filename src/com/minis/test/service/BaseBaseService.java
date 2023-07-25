package com.minis.test.service;

/**
 * @Title: BaseBaseService
 * @Package: com.minis.test
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/25 - 14:23
 */
public class BaseBaseService {
    public AServiceImpl getAs() {
        return as;
    }

    public void setAs(AServiceImpl as) {
        this.as = as;
    }

    private AServiceImpl as;

    public BaseBaseService(){}

    public void sayHello(){
        System.out.println("Base Base Service says hello");
    }
}
