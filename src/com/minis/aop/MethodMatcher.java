package com.minis.aop;

import java.lang.reflect.Method;

/**
 * @Title: MethodMatcher
 * @Package: com.minis.aop
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/12 - 15:50
 */
public interface MethodMatcher {
    boolean matches(Method method, Class<?> targetClass);
}
