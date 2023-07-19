package com.minis.beans.factory.config;

import com.minis.beans.BeansException;
import com.minis.beans.factory.BeanFactory;

/**
 * @Title: BeanFactoryPostProcessor
 * @Package: com.minis.beans.factory.config
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/19 - 15:06
 */
public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(BeanFactory beanFactory) throws BeansException;
}
