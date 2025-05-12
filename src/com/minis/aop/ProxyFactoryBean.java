package com.minis.aop;

import com.minis.beans.BeansException;
import com.minis.beans.factory.BeanFactory;
import com.minis.beans.factory.FactoryBean;
import com.minis.util.ClassUtils;

/**
 * @Title: ProxyFactoryBean
 * @Package: com.minis.aop
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/12 - 10:28
 */
public class ProxyFactoryBean implements FactoryBean<Object> {
    private AopProxyFactory aopProxyFactory;
    private String targetName;
    private Object target;
    private ClassLoader proxyClassLoader = ClassUtils.getDefaultClassLoader();
    private Object singletonInstance;
    private String interceptorName;
    private PointcutAdvisor advisor;
    private BeanFactory beanFactory;

    public ProxyFactoryBean(){
        this.aopProxyFactory = new DefaultAopProxyFactory();
    }

    @Override
    public Object getObject() throws Exception {
        initializeAdvisor();
        return getSingletonInstance();
    }

    private synchronized void initializeAdvisor(){
        Object advice = null;
        MethodInterceptor mi = null;
        try {
            advice = this.beanFactory.getBean(this.interceptorName);
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
        this.advisor = (PointcutAdvisor) advisor;
    }

    private synchronized Object getSingletonInstance() {
        if (this.singletonInstance == null) {
            this.singletonInstance = getProxy(createAopProxy());
        }
        return this.singletonInstance;
    }

    protected Object getProxy(AopProxy aopProxy) {
        return aopProxy.getProxy();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    public AopProxyFactory getAopProxyFactory() {
        return aopProxyFactory;
    }

    public void setAopProxyFactory(AopProxyFactory aopProxyFactory) {
        this.aopProxyFactory = aopProxyFactory;
    }

    protected AopProxy createAopProxy() {
        return getAopProxyFactory().createAopProxy(target,this.advisor);
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object getTarget() {
        return target;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void setInterceptorName(String interceptorName) {
        this.interceptorName = interceptorName;
    }
}
