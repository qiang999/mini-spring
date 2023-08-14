package com.minis.context;

import com.minis.beans.*;
import com.minis.beans.factory.BeanFactory;
import com.minis.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.minis.beans.factory.config.AutowireCapableBeanFactory;
import com.minis.beans.factory.config.BeanFactoryPostProcessor;
import com.minis.beans.factory.config.BeanPostProcessor;
import com.minis.beans.factory.config.ConfigurableListableBeanFactory;
import com.minis.beans.factory.support.*;
import com.minis.beans.factory.support.ApplicationEventPublisher;
import com.minis.beans.factory.xml.XmlBeanDefinitionReader;
import com.minis.core.ClassPathXmlResource;
import com.minis.core.Resource;
import com.minis.core.env.Environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Title: ClassPathXmlApplicationContext
 * @Package: com.minis.context
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/9 - 16:41
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext{
    DefaultListableBeanFactory beanFactory;
    private final List<BeanFactoryPostProcessor> beanFactoryPostProcessors = new ArrayList<>();

    public ClassPathXmlApplicationContext(String fileName){
        this(fileName, true);
    }

    public ClassPathXmlApplicationContext(String fileName, boolean isRefresh){
        Resource resource = new ClassPathXmlResource(fileName);
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        this.beanFactory = beanFactory;
        if (isRefresh){
            try {
                refresh();
            } catch (BeansException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Boolean containsBean(String name) {
        return null;
    }

    @Override
    public boolean isSingleton(String name) {
        return false;
    }

    @Override
    public boolean isPrototype(String name) {
        return false;
    }

    @Override
    public Class<?> getType(String name) {
        return null;
    }

    @Override
    public void registerBean(String beanName, Object obj) {

    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return false;
    }

    @Override
    public int getBeanDefinitionCount() {
        return 0;
    }

    @Override
    public String[] getBeanNamesForType(Class<?> type) {
        return new String[0];
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return null;
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {

    }

    @Override
    public int getBeanPostProcessorCount() {
        return 0;
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
    public void registerSingleton(String beanName, Object singletonObject) {

    }

    @Override
    public Object getSingleton(String beanName) {
        return null;
    }

    @Override
    public boolean containsSingleton(String beanName) {
        return false;
    }

    @Override
    public String[] getSingletonNames() {
        return new String[0];
    }

    @Override
    public void registerListeners() {
        ApplicationListener listener = new ApplicationListener();
        this.getApplicationEventPublisher().addApplicationListener(listener);
    }

    @Override
    public void initApplicationEventPublisher() {
        ApplicationEventPublisher aep = new SimpleApplicationEventPublisher();
        this.setApplicationEventPublisher(aep);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {

    }

    @Override
    public void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        this.beanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
    }

    @Override
    public void onRefresh() {
        this.beanFactory.refresh();
    }

    @Override
    public void finishRefresh() {
        publishEvent(new ContextRefreshEvent("Context Refreshed..."));
    }

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException {
        return this.beanFactory;
    }

    @Override
    public void setEnvironment(Environment environment) {

    }

    @Override
    public Environment getEnvironment() {
        return null;
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        this.getApplicationEventPublisher().publishEvent(event);
    }
    @Override
    public void addApplicationListener(ApplicationListener listener){
        this.getApplicationEventPublisher().addApplicationListener(listener);
    }

    public void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor){
        this.beanFactoryPostProcessors.add(postProcessor);
    }
//    AutowireCapableBeanFactory beanFactory;
//    public ClassPathXmlApplicationContext(String fileName) throws BeansException, IllegalAccessException {
////        Resource resource = new ClassPathXmlResource(fileName);
////        SimpleBeanFactory beanFactory = new SimpleBeanFactory();
////        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
////        reader.loadBeanDefinitions(resource);
////        this.beanFactory = beanFactory;
//        this(fileName, true);
//    }
//
//    public ClassPathXmlApplicationContext(String fileName, boolean isRefresh) throws BeansException, IllegalAccessException {
//        Resource resource = new ClassPathXmlResource(fileName);
//        AutowireCapableBeanFactory beanFactory = new AutowireCapableBeanFactory();
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
//        reader.loadBeanDefinitions(resource);
//        this.beanFactory = beanFactory;
//        if (isRefresh){
//            refresh();
//        }
//    }
//
//    @Override
//    public Object getBean(String beanName) throws BeansException {
//        return this.beanFactory.getBean(beanName);
//    }
//
//    @Override
//    public Boolean containsBean(String name) {
//        return this.beanFactory.containsBean(name);
//    }
//
//    @Override
//    public boolean isSingleton(String name) {
//        return false;
//    }
//
//    @Override
//    public boolean isPrototype(String name) {
//        return false;
//    }
//
//    @Override
//    public Class<?> getType(String name) {
//        return null;
//    }
//
//    @Override
//    public void registerBean(String beanName, Object obj) {
//        this.beanFactory.registerBean(beanName, obj);
//    }
//
//    @Override
//    public void publishEvent(ApplicationEvent event) {}
//
//    public void refresh() throws BeansException, IllegalAccessException{
//        registerBeanPostProcessors(this.beanFactory);
//    }
//
//    private void registerBeanPostProcessors(AutowireCapableBeanFactory beanFactory){
//        beanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
//    }
//
//    private void onRefresh(){
//        this.beanFactory.refresh();
//    }
}
