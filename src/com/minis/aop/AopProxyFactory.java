package com.minis.aop;

/**
 * @Title: AopProxyFactory
 * @Package: com.minis.aop
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/12 - 10:30
 */
public interface AopProxyFactory {
    AopProxy createAopProxy(Object target,PointcutAdvisor advisor);
}
