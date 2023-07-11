package com.minis.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title: SimpleBeanFactory
 * @Package: com.minis.beans
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/10 - 14:55
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory, BeanDefinitionRegistry{
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
    private List<String> beanDefinitionNames = new ArrayList<>();

    public SimpleBeanFactory(){}

    @Override
    public Object getBean(String beanName) throws BeansException {
        Object singleton = this.getSingleton(beanName);
        if (singleton == null){
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (beanDefinition == null){
                throw new BeansException("No bean.");
            }
            try {
                singleton = Class.forName(beanDefinition.getClassName()).newInstance();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            this.registerSingleton(beanName, singleton);
        }
        return singleton;
    }

    @Override
    public Boolean containsBean(String name) {
        return containsSingleton(name);
    }

    @Override
    public void registerBean(String beanName, Object obj) {
        this.registerSingleton(beanName, obj);
    }
//    private List<BeanDefinition> beanDefinitions = new ArrayList<>();
//    private List<String> beanNames = new ArrayList<>();
//    private Map<String, Object> singletons = new HashMap<>();
//    public SimpleBeanFactory(){
//    }
//
//    @Override
//    public Object getBean(String beanName) throws BeansException {
//        Object singleton = singletons.get(beanName);
//        if (singleton == null){
//            int i = beanNames.indexOf(beanName);
//            if (i == -1){
//                throw new BeansException("");
//            } else {
//                BeanDefinition beanDefinition = beanDefinitions.get(i);
//                try {
//                    singleton = Class.forName(beanDefinition.getClassName()).newInstance();
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//                singletons.put(beanDefinition.getId(), singleton);
//            }
//        }
//        return singleton;
//    }
//
    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(name, beanDefinition);
        this.beanDefinitionNames.add(name);
        if (!beanDefinition.isLazyInit()){
            try {
                getBean(name);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void removeBeanDefinition(String name){
        this.beanDefinitionMap.remove(name);
        this.beanDefinitionNames.remove(name);
        this.removeSingleton(name);
    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return this.beanDefinitionMap.get(name);
    }

    public boolean containsBeanDefinition(String name){
        return this.beanDefinitionMap.containsKey(name);
    }

    public boolean isSingleton(String name){
        return this.beanDefinitionMap.get(name).isSingleton();
    }

    public boolean isPrototype(String name){
        return this.beanDefinitionMap.get(name).isPrototype();
    }

    public Class<?> getType(String name){
        return this.beanDefinitionMap.get(name).getClass();
    }
}
