package com.minis.aop;

/**
 * @Title: DefaultAopProxyFactory
 * @Package: com.minis.aop
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/12 - 10:46
 */
public class DefaultAopProxyFactory implements AopProxyFactory {
    @Override
    public AopProxy createAopProxy(Object target,PointcutAdvisor advisor) {
        return new JdkDynamicAopProxy(target,advisor);
    }
}
