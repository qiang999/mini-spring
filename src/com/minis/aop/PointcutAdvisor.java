package com.minis.aop;

/**
 * @Title: PointcutAdvisor
 * @Package: com.minis.aop
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/12 - 15:52
 */
public interface PointcutAdvisor extends Advisor {
    Pointcut getPointcut();
}
