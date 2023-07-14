package com.minis.beans;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
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
    private final Map<String, Object> earlySingletonObjects = new HashMap<String,Object>(16);

    public SimpleBeanFactory(){}

    @Override
    public Object getBean(String beanName) throws BeansException {
        Object singleton = this.getSingleton(beanName);
        if (singleton == null){
            singleton = this.earlySingletonObjects.get(beanName);
            if (singleton == null){
                BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
                singleton = createBean(beanDefinition);
                this.registerBean(beanName, singleton);
//                if (beanDefinition == null){
//                    throw new BeansException("No bean.");
//                }
//                try {
//                    singleton = Class.forName(beanDefinition.getClassName()).newInstance();
//                } catch (ClassNotFoundException e) {
//                    throw new RuntimeException(e);
//                } catch (InstantiationException e) {
//                    throw new RuntimeException(e);
//                } catch (IllegalAccessException e) {
//                    throw new RuntimeException(e);
//                }
//                this.registerSingleton(beanName, singleton);
            }
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
            ArgumentValues argumentValues = beanDefinition.getConstructorArgumentValues();
            if (!argumentValues.isEmpty()){
                Class<?>[] paramTypes = new Class<?>[argumentValues.getArgumentValueCount()];
                Object[] paramValues = new Object[argumentValues.getArgumentValueCount()];
                for (int i = 0;i<argumentValues.getArgumentValueCount();i++) {
                    ArgumentValue argumentValue = argumentValues.getIndexedArgumentValue(i);
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

//        PropertyValues propertyValues = beanDefinition.getPropertyValues();
//        if (!propertyValues.isEmpty()){
//            for (int i = 0; i< propertyValues.size(); i++){
//                PropertyValue propertyValue = propertyValues.getPropertyValueList().get(i);
//                String pType = propertyValue.getType();
//                String pName = propertyValue.getName();
//                Object pValue = propertyValue.getValue();
//                Class<?>[] paramTypes = new Class<?>[1];
//                if ("String".equals(pType)||"java.lang.String".equals(pType)){
//                    paramTypes[0] = String.class;
//                }else if ("Integer".equals(pType)||"java.lang.Integer".equals(pType)){
//                    paramTypes[0] = Integer.class;
//                }else if ("int".equals(pType)){
//                    paramTypes[0] = int.class;
//                }else {
//                    paramTypes[0] = String.class;
//                }
//                Object[] paramValues = new Object[1];
//                paramValues[0] = pValue;
//
//                String methodName = "set" + pName.substring(0, 1).toUpperCase()+pName.substring(1);
//                Method method = null;
//                try {
//                    method = clz.getMethod(methodName, paramTypes);
//                    try {
//                        method.invoke(obj, paramValues);
//                    } catch (Exception e) {
//                        throw new RuntimeException(e);
//                    }
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
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
}
