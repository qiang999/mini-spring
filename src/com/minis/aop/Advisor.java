package com.minis.aop;

/**
 * @Title: Advisor
 * @Package: com.minis.aop
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/12 - 11:22
 */
public interface Advisor {
    MethodInterceptor getMethodInterceptor();
    void setMethodInterceptor(MethodInterceptor methodInterceptor);
}
