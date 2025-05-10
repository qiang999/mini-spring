package com.minis.web;

import com.minis.beans.BeansException;
import com.minis.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.minis.beans.factory.config.BeanFactoryPostProcessor;
import com.minis.beans.factory.config.ConfigurableListableBeanFactory;
import com.minis.beans.factory.support.BeanDefinition;
import com.minis.beans.factory.support.DefaultListableBeanFactory;
import com.minis.context.*;

import javax.servlet.ServletContext;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: AnnotationConfigWebApplicationContext
 * @Package: com.minis.web
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/9 - 11:42
 */
public class AnnotationConfigWebApplicationContext extends AbstractApplicationContext implements WebApplicationContext {
    private WebApplicationContext parentWebApplicationContext;
    private ServletContext servletContext;
    DefaultListableBeanFactory beanFactory;

    private final List<BeanFactoryPostProcessor>  beanFactoryPostProcessors = new ArrayList<BeanFactoryPostProcessor>();

    public AnnotationConfigWebApplicationContext(String fileName) {
        this(fileName,null);
    }

    public AnnotationConfigWebApplicationContext(String fileName, WebApplicationContext parentWebApplicationContext) {
        this.parentWebApplicationContext = parentWebApplicationContext;
        this.servletContext = this.parentWebApplicationContext.getServletContext();
        URL xmlPath = null;
        try {
            xmlPath = this.getServletContext().getResource(fileName);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        List<String> packageNames = XmlScanComponentHelper.getNodeValue(xmlPath);
        List<String> controllerNames = scanPackages(packageNames);
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        this.beanFactory = beanFactory;
        this.beanFactory.setParent(this.parentWebApplicationContext.getBeanFactory());
        loadBeanDefinitions(controllerNames);

        if (true){
            try {
                refresh();
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }

    }

    public void loadBeanDefinitions(List<String> controllerNames) {
        for (String controller:controllerNames){
            String beanID = controller;
            String beanClassName = controller;
            BeanDefinition beanDefinition = new BeanDefinition(beanID,beanClassName);
            this.beanFactory.registerBeanDefinition(beanID,beanDefinition);
        }
    }

    private List<String> scanPackages(List<String> packages){
        List<String> tempControllerNames = new ArrayList<>();
        for (String packageName : packages) {
            tempControllerNames.addAll(scanPackage(packageName));
        }
        return tempControllerNames;
    }

    private List<String> scanPackage(String packageName){
        List<String> tempControllerNames = new ArrayList<>();
        URL url  =this.getClass().getClassLoader().getResource("/"+packageName.replaceAll("\\.", "/"));
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                scanPackage(packageName+"."+file.getName());
            }else {
                String controllerName = packageName+"."+file.getName().replace(".class","");
                tempControllerNames.add(controllerName);
            }
        }
        return tempControllerNames;
    }

    public void setParent(WebApplicationContext parentWebApplicationContext) {
        this.parentWebApplicationContext = parentWebApplicationContext;
        this.beanFactory.setParent(this.parentWebApplicationContext.getBeanFactory());
    }

    @Override
    public ServletContext getServletContext() {
        return this.servletContext;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public void registerListeners() {
        ApplicationListener listener = new ApplicationListener();
        this.getApplicationEventPublisher().addApplicationListener(listener);
    }

    @Override
    public void initApplicationEventPublisher() {
        ApplicationEventPublisher publisher = new SimpleApplicationEventPublisher();
        this.setApplicationEventPublisher(publisher);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory bf) {

    }

    @Override
    public void registerBeanPostProcessors(ConfigurableListableBeanFactory bf) {
        this.beanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
    }

    @Override
    public void onRefresh() {
        this.beanFactory.refresh();
    }

    @Override
    public void finishRefresh() {

    }

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException {
        return this.beanFactory;
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        this.getApplicationEventPublisher().publishEvent(event);
    }

    @Override
    public void addApplicationListener(ApplicationListener listener) {
        this.getApplicationEventPublisher().addApplicationListener(listener);
    }
}
