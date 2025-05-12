package com.minis.beans.factory.support;

import com.minis.beans.BeansException;
import com.minis.beans.PropertyValue;
import com.minis.beans.PropertyValues;
import com.minis.beans.factory.BeanFactory;
import com.minis.beans.factory.FactoryBean;
import com.minis.beans.factory.config.ArgumentValue;
import com.minis.beans.factory.config.ArgumentValues;

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
 * @Date: 2025/5/8 - 9:58
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements BeanFactory,BeanDefinitionRegistry {

    protected Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    protected List<String> beanDefinitionNames = new ArrayList<>();

    private final Map<String,Object> earlySingletonObjects = new HashMap<String,Object>(16);

    public void refresh(){
        for(String beanName : beanDefinitionNames){
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
        if (singleton == null) {
            singleton = this.earlySingletonObjects.get(beanName);
            if (singleton == null) {
                System.out.println("get bean null -------------- " + beanName);
                BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
                if (beanDefinition != null) {
                    singleton = createBean(beanDefinition);
                    this.registerSingleton(beanName, singleton);
                    singleton = applyBeanPostProcessorBeforeInitialization(singleton, beanName);
                    if (beanDefinition.getInitMethodName() != null&&!beanDefinition.equals("")) {
                        invokeInitMethod(beanDefinition,singleton);
                    }
                    applyBeanPostProcessorAfterInitialization(singleton, beanName);
                    this.removeSingleton(beanName);
                    this.registerSingleton(beanName,singleton);
                }else {
                    return  null;
                }
            }
        }
        if(singleton==null){
            throw new BeansException("bean is null");
        }
        if (singleton instanceof FactoryBean){
            return this.getObjectForBeanInstance(singleton,beanName);
        }
        return singleton;
    }

    protected Object getObjectForBeanInstance(Object beanInstance,String beanName){
        if(!(beanInstance instanceof FactoryBean)){
            return  beanInstance;
        }
        Object object = null;
        FactoryBean<?> factory =  (FactoryBean<?>) beanInstance;
        object = getObjectFormFactoryBean(factory,beanName);
        return object;
    }

    private void invokeInitMethod(BeanDefinition beanDefinition,Object object) {
        Class<?> clz = beanDefinition.getClass();
        Method  method = null;
        try {
            method = clz.getMethod(beanDefinition.getInitMethodName());
            method.invoke(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        handleProperties(beanDefinition,clz,obj);
        return obj;
    }

    private Object doCreateBean(BeanDefinition beanDefinition){
        Class<?> clz = null;
        Object obj = null;
        Constructor<?> con = null;
        try {
            clz = Class.forName(beanDefinition.getClassName());
            //处理构造器函数
            ArgumentValues argumentValues = beanDefinition.getArgumentValues();
            //有参数
            if(argumentValues!=null&&!argumentValues.isEmpty()){
                Class<?>[] paramTypes = new Class<?>[argumentValues.getArgumentCount()];
                Object[] paramValues = new Object[argumentValues.getArgumentCount()];
                for (int i = 0; i < argumentValues.getArgumentCount(); i++) {
                    ArgumentValue argumentValue = argumentValues.getIndexedArgumentValue(i);
                    if ("String".equals(argumentValue.getType())||"java.lang.String".equals(argumentValue.getType())) {
                        paramTypes[i] = String.class;
                        paramValues[i] = argumentValue.getValue();
                    }else if ("Integer".equals(argumentValue.getType())||"java.lang.Integer".equals(argumentValue.getType())) {
                        paramTypes[i] = Integer.class;
                        paramValues[i] = Integer.valueOf((String) argumentValue.getValue());
                    }else if("int".equals(argumentValue.getType())){
                        paramTypes[i] = int.class;
                        paramValues[i] = Integer.valueOf((String) argumentValue.getValue());
                    }else{
                        paramTypes[i] = String.class;
                        paramValues[i] = argumentValue.getValue();
                    }
                }
                con = clz.getConstructor(paramTypes);
                obj = con.newInstance(paramValues);
            }else {
                obj = clz.newInstance();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    private void handleProperties(BeanDefinition beanDefinition,Class<?> clz,Object obj){
        //处理属性
        System.out.println("handle properties for bean : " + beanDefinition.getId());
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        if (propertyValues!=null&&!propertyValues.isEmpty()){
            for (int i = 0; i < propertyValues.size(); i++) {
                PropertyValue propertyValue = propertyValues.getPropertyValueList().get(i);
                String pType = propertyValue.getType();
                String pName = propertyValue.getName();
                Object pValue = propertyValue.getValue();
                boolean isRef = propertyValue.isRef();
                Class<?>[] paramTypes = new Class<?>[1];
                Object[] paramValues = new Object[1];
                if (!isRef) {
                    if ("String".equals(pType)||"java.lang.String".equals(pType)) {
                        paramTypes[0] = String.class;
                    }else if ("Integer".equals(pType)||"java.lang.Integer".equals(pType)) {
                        paramTypes[0] = Integer.class;
                    } else if ("int".equals(pType)) {
                        paramTypes[0] = int.class;
                    }else {
                        paramTypes[0] = String.class;
                    }

                    paramValues[0] =  pValue;
                }else {
                    try {
                        paramTypes[0] = Class.forName(pType);
                        paramValues[0] =  getBean((String) pValue);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                String methodName = "set" + pName.substring(0, 1).toUpperCase() + pName.substring(1);
                Method method = null;
                try {
                    method = clz.getMethod(methodName, paramTypes);
                    method.invoke(obj, paramValues);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanName, beanDefinition);
        this.beanDefinitionNames.add(beanName);
        if (!beanDefinition.isLazyInit()){
            try {
                getBean(beanName);
            } catch (BeansException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void removeBeanDefinition(String beanName) {
        this.beanDefinitionMap.remove(beanName);
        this.beanDefinitionNames.remove(beanName);
        this.removeSingleton(beanName);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return this.beanDefinitionMap.get(beanName);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return this.beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public boolean containsBean(String name) {
        return this.containsSingleton(name);
    }

    @Override
    public boolean isSingleton(String name) {
        return this.beanDefinitionMap.get(name).isSingleton();
    }

    @Override
    public boolean isPrototype(String name) {
        return this.beanDefinitionMap.get(name).isPrototype();
    }

    @Override
    public Class<?> getType(String name) {
        return this.beanDefinitionMap.get(name).getClass();
    }

    abstract public Object applyBeanPostProcessorBeforeInitialization(Object existingBean,String beanName) throws BeansException;

    abstract public Object applyBeanPostProcessorAfterInitialization(Object existingBean,String beanName) throws BeansException;
}
