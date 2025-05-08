package com.minis.beans.factory.config;

/**
 * @Title: SingletonBeanRegistry
 * @Package: com.minis.beans
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/6 - 17:47
 */
public interface SingletonBeanRegistry {

    void registerSingleton(String beanName, Object obj);

    Object getSingleton(String beanName);

    boolean containsSingleton(String beanName);

    String[] getSingletonNames();
}
