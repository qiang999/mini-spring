package com.minis.aop;

/**
 * @Title: Pointcut
 * @Package: com.minis.aop
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/12 - 15:51
 */
public interface Pointcut {
    MethodMatcher getMethodMatcher();
}
