package com.minis.beans.factory.config;

import com.minis.beans.factory.BeanFactory;

/**
 * @Title: ConfigurableBeanFactory
 * @Package: com.minis.beans.factory.config
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/8 - 11:11
 */
public interface ConfigurableBeanFactory extends BeanFactory,SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
    int getBeanPostProcessorCount();
    void registerDependentBean(String beanName, String dependentBeanName);
    String[] getDependentBeans(String beanName);
    String[] getDependenciesForBean(String beanName);
}
