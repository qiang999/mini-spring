package com.minis.web.servlet;

import java.lang.reflect.Method;

/**
 * @Title: HandlerMethod
 * @Package: com.minis.web.servlet
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/27 - 14:08
 */
public class HandlerMethod {
    private Object bean;
    private Class<?> beanType;

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    private Method method;
    private MethodParameter[] parameters;
    private Class<?> returnType;
    private String description;
    private String className;
    private String methodName;

    public HandlerMethod(Method method, Object obj, Class<?> clz, String methodName){
        this.method = method;
        this.bean = obj;
        this.beanType = clz;
        this.methodName = methodName;
    }
}
