package com.minis.test;

/**
 * @Title: BaseBaseService
 * @Package: com.minis.test
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/14 - 15:13
 */
public class BaseBaseService {
    public AServiceImpl getAs() {
        return as;
    }

    public void setAs(AServiceImpl as) {
        this.as = as;
    }

    private AServiceImpl as;
}
