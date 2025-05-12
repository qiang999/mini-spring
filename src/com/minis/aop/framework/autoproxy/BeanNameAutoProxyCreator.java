package com.minis.aop.framework.autoproxy;

import com.minis.aop.AopProxyFactory;
import com.minis.aop.DefaultAopProxyFactory;
import com.minis.aop.PointcutAdvisor;
import com.minis.aop.ProxyFactoryBean;
import com.minis.beans.BeansException;
import com.minis.beans.factory.BeanFactory;
import com.minis.beans.factory.config.BeanPostProcessor;
import com.minis.util.PatternMatchUtils;

/**
 * @Title: BeanNameAutoProxyCreator
 * @Package: com.minis.aop.framework.autoproxy
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/12 - 16:26
 */
public class BeanNameAutoProxyCreator implements BeanPostProcessor {
    String pattern;
    private BeanFactory beanFactory;
    private AopProxyFactory aopProxyFactory;
    private String interceptorName;
    private PointcutAdvisor advisor;

    public BeanNameAutoProxyCreator() {
        this.aopProxyFactory = new DefaultAopProxyFactory();
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (isMatch(beanName,this.pattern)){
            ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
            proxyFactoryBean.setTarget(bean);
            proxyFactoryBean.setBeanFactory(beanFactory);
            proxyFactoryBean.setAopProxyFactory(aopProxyFactory);
            proxyFactoryBean.setInterceptorName(interceptorName);
            return proxyFactoryBean;
        }else  {
            return bean;
        }
    }

    protected boolean isMatch(String beanName,String mappedName) {
        return PatternMatchUtils.simpleMatch(mappedName,beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
