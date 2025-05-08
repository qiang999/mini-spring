package com.minis.beans.factory;

import com.minis.beans.BeansException;

/**
 * @Title: BeanFactory
 * @Package: com.minis.beans
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/6 - 16:14
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    boolean containsBean(String name);

    //void registerBean(String beanName, Object obj);

    boolean isSingleton(String name);

    boolean isPrototype(String name);

    Class<?>  getType(String name);

}
