package com.minis.beans.factory;

/**
 * @Title: FactoryBean
 * @Package: com.minis.beans.factory
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/12 - 10:06
 */
public interface FactoryBean<T> {
    T getObject() throws Exception;
    Class<?> getObjectType();
    default  boolean isSingleton() {
        return true;
    }
}
