package com.minis.beans.factory.support;

import com.minis.beans.BeansException;
import com.minis.beans.factory.FactoryBean;

/**
 * @Title: FactoryBeanRegistrySupport
 * @Package: com.minis.beans.factory.support
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/12 - 10:08
 */
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry{
    protected Class<?> getTypeForFactoryBean(final FactoryBean<?> factoryBean){
        return factoryBean.getObjectType();
    }

    protected Object getObjectFormFactoryBean(FactoryBean<?> factory,String beanName){
        Object object = doGetObjectFormFactoryBean(factory,beanName);
        try {
            object = postProcessObjectFromFactoryBean(object,beanName);
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
        return object;
    }

    protected Object postProcessObjectFromFactoryBean(Object object,String beanName) throws BeansException {
        return object;
    }

    private Object doGetObjectFormFactoryBean(final FactoryBean<?> factory,String beanName){
        Object object = null;
        try {
            object = factory.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return object;
    }
}
