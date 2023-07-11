package com.minis.beans;

/**
 * @Title: SingletonBeanRegistry
 * @Package: com.minis.beans
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/10 - 16:47
 */
public interface SingletonBeanRegistry {
    void registerSingleton(String beanName, Object singletonObject);
    Object getSingleton(String beanName);
    boolean containsSingleton(String beanName);
    String[] getSingletonNames();
}
