package com.minis.beans.factory.config;

import com.minis.beans.BeansException;

/**
 * @Title: BeanPostProcessor
 * @Package: com.minis.beans.factory.config
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/8 - 9:41
 */
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
