package com.minis.beans.factory.config;

import com.minis.beans.BeansException;
import com.minis.beans.factory.BeanFactory;

/**
 * @Title: BeanFactoryPostProcessor
 * @Package: com.minis.beans.factory.config
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/8 - 10:36
 */
public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(BeanFactory beanFactory) throws BeansException;
}
