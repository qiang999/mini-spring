package com.minis.aop;

import java.lang.reflect.Method;

/**
 * @Title: MethodBeforeAdvice
 * @Package: com.minis.aop
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/12 - 15:32
 */
public interface MethodBeforeAdvice extends BeforeAdvice{
    void before(Method method, Object[] args, Object target) throws Throwable;
}
