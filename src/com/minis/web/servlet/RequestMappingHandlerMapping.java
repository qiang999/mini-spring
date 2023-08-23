package com.minis.web.servlet;

import com.minis.beans.BeansException;
import com.minis.context.ApplicationContext;
import com.minis.context.ApplicationContextAware;
import com.minis.web.RequestMapping;
import com.minis.web.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @Title: RequestMappingHandlerMapping
 * @Package: com.minis.web.servlet
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/27 - 14:27
 */
public class RequestMappingHandlerMapping implements HandlerMapping, ApplicationContextAware {
    ApplicationContext applicationContext;
    private MappingRegistry mappingRegistry = null;

    public RequestMappingHandlerMapping(){}

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public HandlerMethod getHandler(HttpServletRequest request) throws Exception {
        if (this.mappingRegistry==null){
            this.mappingRegistry = new MappingRegistry();
            initMappings();
        }
        String sPath = request.getServletPath();
        if (!this.mappingRegistry.getUrlMappingNames().contains(sPath)){
            return null;
        }
        Method method = this.mappingRegistry.getMappingMethods().get(sPath);
        Object obj = this.mappingRegistry.getMappingObjs().get(sPath);
        Class<?> clz = this.mappingRegistry.getMappingClasses().get(sPath);
        String methodName = this.mappingRegistry.getMappingMethodNames().get(sPath);
        HandlerMethod handlerMethod = new HandlerMethod(method,obj,clz,methodName);
        return handlerMethod;
    }

    protected void initMappings(){
        Class<?> clz = null;
        Object obj = null;
        String[] controllerNames = this.applicationContext.getBeanDefinitionNames();
        for (String controllerName:
             controllerNames) {
            try {
                clz = Class.forName(controllerName);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                obj = this.applicationContext.getBean(controllerName);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Method[] methods = clz.getDeclaredMethods();
            if (methods!=null){
                for (Method method:
                     methods) {
                    boolean isRequestMapping = method.isAnnotationPresent(RequestMapping.class);
                    if (isRequestMapping){
                        String methodName = method.getName();
                        String urlMapping = method.getAnnotation(RequestMapping.class).value();
                        this.mappingRegistry.getUrlMappingNames().add(urlMapping);
                        this.mappingRegistry.getMappingObjs().put(urlMapping,obj);
                        this.mappingRegistry.getMappingMethods().put(urlMapping,method);
                        this.mappingRegistry.getMappingMethodNames().put(urlMapping,methodName);
                        this.mappingRegistry.getMappingClasses().put(urlMapping,clz);
                    }
                }
            }
        }
    }
}
