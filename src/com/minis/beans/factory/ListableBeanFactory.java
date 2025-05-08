package com.minis.beans.factory;

import com.minis.beans.BeansException;

import java.util.Map;

/**
 * @Title: ListableBeanFactory
 * @Package: com.minis.beans.factory
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/8 - 10:58
 */
public interface ListableBeanFactory extends BeanFactory {
    boolean containsBeanDefinition(String beanName);
    int getBeanDefinitionCount();
    String[] getBeanDefinitionNames();
    String[] getBeanNamesForType(Class<?> type);
    <T>Map<String,T> getBeansOfType(Class<T> type) throws BeansException;
}
