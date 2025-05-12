package com.minis.aop;

/**
 * @Title: MethodInterceptor
 * @Package: com.minis.aop
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/12 - 11:14
 */
public interface MethodInterceptor extends Interceptor{
    Object invoke(MethodInvocation invocation) throws Throwable;
}
