package com.minis.aop;

/**
 * @Title: DefaultAdvisor
 * @Package: com.minis.aop
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/12 - 11:28
 */
public class DefaultAdvisor implements Advisor {
    private MethodInterceptor methodInterceptor;

    public DefaultAdvisor(){}

    @Override
    public MethodInterceptor getMethodInterceptor() {
        return this.methodInterceptor;
    }

    @Override
    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }
}
