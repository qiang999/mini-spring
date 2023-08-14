package com.minis.context;

import com.minis.beans.BeansException;
import com.minis.beans.factory.config.BeanFactoryPostProcessor;
import com.minis.beans.factory.config.ConfigurableListableBeanFactory;
import com.minis.beans.factory.support.ApplicationEventPublisher;
import com.minis.core.env.Environment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Title: AbstractApplicationContext
 * @Package: com.minis.context
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/19 - 14:59
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    private Environment environment;
    private final List<BeanFactoryPostProcessor> beanFactoryPostProcessors = new ArrayList<>();
    private long startupDate;
    private final AtomicBoolean active = new AtomicBoolean();
    private final AtomicBoolean closed = new AtomicBoolean();
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public Object getBean(String beanName) throws BeansException{
        return getBeanFactory().getBean(beanName);
    }
    public List<BeanFactoryPostProcessor> getBeanFactoryPostProcessors(){
        return this.beanFactoryPostProcessors;
    }

    public void refresh() throws BeansException, IllegalStateException{
        postProcessBeanFactory(getBeanFactory());
        registerBeanPostProcessors(getBeanFactory());
        initApplicationEventPublisher();
        onRefresh();
        registerListeners();
        finishRefresh();
    }

    public abstract void registerListeners();
    public abstract void initApplicationEventPublisher();
    public abstract void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory);
    public abstract void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory);
    public abstract void onRefresh();
    public abstract void finishRefresh();

    @Override
    public String getApplicationName(){
        return "";
    }
    @Override
    public long getStartupDate(){
        return this.startupDate;
    }
    @Override
    public abstract ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException;
    @Override
    public void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor){
        this.beanFactoryPostProcessors.add(postProcessor);
    }
    @Override
    public void close(){}
    @Override
    public boolean isActive(){
        return true;
    }

    public ApplicationEventPublisher getApplicationEventPublisher(){
        return applicationEventPublisher;
    }
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher){
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public String[] getBeanDefinitionNames(){
        return getBeanFactory().getBeanDefinitionNames();
    }
}
