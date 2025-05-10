package com.minis.web.servlet;

import com.minis.beans.BeansException;
import com.minis.web.RequestMapping;
import com.minis.web.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @Title: RequestMappingHandlerMapping
 * @Package: com.minis.web.servlet
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/9 - 16:51
 */
public class RequestMappingHandlerMapping implements HandlerMapping {
    WebApplicationContext wac;
    private final MappingRegistry mappingRegistry = new MappingRegistry();
    public RequestMappingHandlerMapping(WebApplicationContext wac) {
        this.wac = wac;
        initMapping();
    }

    @Override
    public HandlerMethod getHandler(HttpServletRequest request) throws Exception {
        String sPath = request.getServletPath();
        if (!this.mappingRegistry.getUrlMappingNames().contains(sPath)) {
            return null;
        }
        Method method = this.mappingRegistry.getMappingMethods().get(sPath);
        Object obj = this.mappingRegistry.getMappingObjs().get(sPath);
        HandlerMethod handlerMethod = new HandlerMethod(method, obj);
        return handlerMethod;
    }

    protected void initMapping(){
        Class<?> clazz = null;
        Object obj = null;
        String[] controllerNames = this.wac.getBeanDefinitionNames();
        for (String controllerName:controllerNames){
            try {
                clazz = Class.forName(controllerName);
                obj = this.wac.getBean(controllerName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (BeansException e) {
                e.printStackTrace();
            }
            Method[] methods = clazz.getDeclaredMethods();
            if (methods!=null){
                for (Method method:methods){
                    boolean isRequestMapping = method.isAnnotationPresent(RequestMapping.class);
                    if (isRequestMapping){
                        String methodName = method.getName();
                        String urlMapping = method.getAnnotation(RequestMapping.class).value();
                        this.mappingRegistry.getUrlMappingNames().add(urlMapping);
                        this.mappingRegistry.getMappingObjs().put(urlMapping,obj);
                        this.mappingRegistry.getMappingMethods().put(urlMapping,method);
                    }
                }
            }
        }
    }
}
