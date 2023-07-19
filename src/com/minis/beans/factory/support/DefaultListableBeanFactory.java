package com.minis.beans.factory.support;

import com.minis.beans.BeansException;
import com.minis.beans.factory.config.AbstractAutowireCapableBeanFactory;
import com.minis.beans.factory.config.BeanDefinition;
import com.minis.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: DefaultListableBeanFactory
 * @Package: com.minis.beans.factory.support
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/18 - 16:40
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements ConfigurableListableBeanFactory {
    @Override
    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return (String[]) this.beanDefinitionNames.toArray();
    }

    @Override
    public String[] getBeanNamesForType(Class<?> type) {
        List<String> result = new ArrayList<>();
        for (String beanName:
             this.beanDefinitionNames) {
            boolean matchFound = false;
            BeanDefinition mbd = this.getBeanDefinition(beanName);
            Class<?> classToNatch = mbd.getClass();
            if (type.isAssignableFrom(classToNatch)){
                matchFound = true;
            }else {
                matchFound = false;
            }
            if (matchFound){
                result.add(beanName);
            }
        }
        return (String[]) result.toArray();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        String[] beanNames = getBeanNamesForType(type);
        Map<String, T> result = new LinkedHashMap<>(beanNames.length);
        for (String beanName:
             beanNames) {
            Object beanInstance = getBean(beanName);
            result.put(beanName, (T)beanInstance);
        }
        return result;
    }

    @Override
    public void registerDependentBean(String beanName, String dependentBeanName) {

    }

    @Override
    public String[] getDependentBeans(String beanName) {
        return new String[0];
    }

    @Override
    public String[] getDependenciesForBean(String beanName) {
        return new String[0];
    }

    @Override
    public Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName) throws BeansException {
        return null;
    }
}
