package com.minis.beans.factory.support;

import com.minis.beans.BeansException;
import com.minis.beans.PropertyValue;
import com.minis.beans.PropertyValues;
import com.minis.beans.factory.BeanFactory;
import com.minis.beans.factory.config.BeanDefinition;
import com.minis.beans.factory.config.ConstructorArgumentValue;
import com.minis.beans.factory.config.ConstructorArgumentValues;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title: AbstractBeanFactory
 * @Package: com.minis.beans.factory.support
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/17 - 14:11
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory, BeanDefinitionRegistry {
    protected Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
    protected List<String> beanDefinitionNames = new ArrayList<>();
    private final Map<String, Object> earlySingletonObjects = new HashMap<>(16);
    public AbstractBeanFactory(){}

    public void refresh(){
        for (String beanName:
                beanDefinitionNames) {
            try {
                getBean(beanName);
            } catch (BeansException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        Object singleton = this.getSingleton(beanName);
        if (singleton == null){
            singleton = this.earlySingletonObjects.get(beanName);
            if (singleton == null){
                BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
                singleton = createBean(beanDefinition);
                this.registerBean(beanName, singleton);
                applyBeanPostProcessorBeforeInitialization(singleton, beanName);
                if (beanDefinition.getInitMethodName()!=null&&!beanDefinition.equals("")){
                    invokeInitMethod(beanDefinition, singleton);
                }
                applyBeanPostProcessorAfterInitialization(singleton, beanName);
            }
        }
        return singleton;
    }

    private void invokeInitMethod(BeanDefinition beanDefinition, Object obj){
        Class<?> clz = beanDefinition.getClass();
        Method method = null;
        try {
            method = clz.getMethod(beanDefinition.getInitMethodName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            method.invoke(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    private Object createBean(BeanDefinition beanDefinition){
        Class<?> clz = null;
        Object obj = doCreateBean(beanDefinition);
        this.earlySingletonObjects.put(beanDefinition.getId(), obj);
        try {
            clz = Class.forName(beanDefinition.getClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        handleProperties(beanDefinition, clz, obj);
        return obj;
    }

    private Object doCreateBean(BeanDefinition beanDefinition){
        Class<?> clz = null;
        Object obj = null;
        Constructor<?> con = null;
        try {
            clz = Class.forName(beanDefinition.getClassName());
            ConstructorArgumentValues argumentValues = beanDefinition.getConstructorArgumentValues();
            if (!argumentValues.isEmpty()){
                Class<?>[] paramTypes = new Class<?>[argumentValues.getArgumentValueCount()];
                Object[] paramValues = new Object[argumentValues.getArgumentValueCount()];
                for (int i = 0;i<argumentValues.getArgumentValueCount();i++) {
                    ConstructorArgumentValue argumentValue = argumentValues.getIndexedArgumentValue(i);
                    if ("String".equals(argumentValue.getType())||"java.lang.String".equals(argumentValue.getType())){
                        paramTypes[i] = String.class;
                        paramValues[i] = argumentValue.getValue();
                    }else if ("Integer".equals(argumentValue.getType())||"java.lang.Integer".equals(argumentValue.getType())){
                        paramTypes[i] = Integer.class;
                        paramValues[i] = Integer.valueOf((String) argumentValue.getType());
                    }else if ("int".equals(argumentValue.getType())){
                        paramTypes[i] = int.class;
                        paramValues[i] = Integer.valueOf((String) argumentValue.getValue());
                    }else {
                        paramTypes[i] = String.class;
                        paramValues[i] = argumentValue.getValue();
                    }
                }
                try {
                    con = clz.getConstructor(paramTypes);
                    obj = con.newInstance(paramValues);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else {
                obj = clz.newInstance();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    private void handleProperties(BeanDefinition bd, Class<?> clz, Object obj){
        System.out.println("handle properties for bean ："+bd.getId());
        PropertyValues propertyValues = bd.getPropertyValues();
        if (!propertyValues.isEmpty()){
            for (int i=0;i<propertyValues.size();i++){
                PropertyValue propertyValue = propertyValues.getPropertyValueList().get(i);
                String pName = propertyValue.getName();
                String pType = propertyValue.getType();
                Object pValue = propertyValue.getValue();
                boolean isRef = propertyValue.isRef();
                Class<?>[] paramTypes = new Class<?>[1];
                Object[] paramValues = new Object[1];
                if (!isRef){
                    if ("String".equals(pType)||"java.lang.String".equals(pType)){
                        paramTypes[0] = String.class;
                    } else if ("Integer".equals(pType)||"java.lang.Integer".equals(pType)) {
                        paramTypes[0] = Integer.class;
                    } else if ("int".equals(pType)) {
                        paramTypes[0] = int.class;
                    }else {
                        paramTypes[0] = String.class;
                    }
                    paramValues[0] = pValue;
                }else {
                    try {
                        paramTypes[0] = Class.forName(pType);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        paramValues[0] = getBean((String) pValue);
                    } catch (BeansException e) {
                        throw new RuntimeException(e);
                    }
                }
                String methodName = "set" + pName.substring(0,1).toUpperCase()+pName.substring(1);
                Method method = null;
                try {
                    method = clz.getMethod(methodName, paramTypes);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                try {
                    method.invoke(obj, paramValues);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
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

    public Map<String, Object> getEarlySingletonObjects() {
        return earlySingletonObjects;
    }

    private void populateBean(BeanDefinition beanDefinition, Class<?> clz, Object obj){
        handleProperties(beanDefinition, clz, obj);
    }

    abstract public Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    abstract public Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
