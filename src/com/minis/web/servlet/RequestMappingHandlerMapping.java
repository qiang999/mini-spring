package com.minis.web.servlet;

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
public class RequestMappingHandlerMapping implements HandlerMapping {
    WebApplicationContext wac;
    private final MappingRegistry  mappingRegistry = new MappingRegistry();
    public RequestMappingHandlerMapping(WebApplicationContext wac){
        this.wac = wac;
        initMapping();
    }

    protected void initMapping(){
        Class<?> clz = null;
        Object obj = null;
        String[] controllerNames = this.wac.getBeanDefinitionNames();
        for (String controllerName:
             controllerNames) {
            try {
                clz = Class.forName(controllerName);
                obj = this.wac.getBean(controllerName);
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
                        this.mappingRegistry.getMappingObjs().put(urlMapping, obj);
                        this.mappingRegistry.getMethodMethods().put(urlMapping, method);
                    }
                }
            }
        }
    }

    @Override
    public HandlerMethod getHandler(HttpServletRequest request) throws Exception {
        String sPath = request.getServletPath();
        if (!this.mappingRegistry.getUrlMappingNames().contains(sPath)){
            return null;
        }
        Method method = this.mappingRegistry.getMethodMethods().get(sPath);
        Object obj = this.mappingRegistry.getMappingObjs().get(sPath);
        HandlerMethod handlerMethod = new HandlerMethod(method, obj);
        return handlerMethod;
    }
}
