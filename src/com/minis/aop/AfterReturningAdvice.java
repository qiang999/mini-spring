package com.minis.aop;

import java.lang.reflect.Method;

/**
 * @Title: AfterReturningAdvice
 * @Package: com.minis.aop
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/12 - 15:34
 */
public interface AfterReturningAdvice extends AfterAdvice{
    void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable;
}
