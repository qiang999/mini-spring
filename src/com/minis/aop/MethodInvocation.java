package com.minis.aop;

import java.lang.reflect.Method;

/**
 * @Title: MethodInvocation
 * @Package: com.minis.aop
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/12 - 11:18
 */
public interface MethodInvocation {
    Method getMethod();
    Object[] getArguments();
    Object getThis();
    Object proceed() throws Throwable;
}
