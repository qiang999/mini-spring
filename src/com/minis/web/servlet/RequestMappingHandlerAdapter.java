package com.minis.web.servlet;

import com.minis.beans.BeansException;
import com.minis.web.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @Title: RequestMappingHandlerAdapter
 * @Package: com.minis.web.servlet
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/9 - 17:01
 */
public class RequestMappingHandlerAdapter implements HandlerAdapter {
    WebApplicationContext wac;
    private WebBindingInitializer webBindingInitializer = null;
    private HttpMessageConverter messageConverter = null;

    public RequestMappingHandlerAdapter() {
    }

    public RequestMappingHandlerAdapter(WebApplicationContext wac) {
        this.wac = wac;
        try {
            this.webBindingInitializer = (WebBindingInitializer) this.wac.getBean("webBindingInitializer");
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpMessageConverter getMessageConverter() {
        return messageConverter;
    }

    public void setMessageConverter(HttpMessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return handleInternal(request,response,(HandlerMethod)handler);
    }

    private ModelAndView handleInternal(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler) {
        ModelAndView modelAndView = null;
        try {
            modelAndView = invokeHandlerMethod(request,response,handler);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return modelAndView;
    }

    protected ModelAndView invokeHandlerMethod(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
        WebDataBinderFactory binderFactory = new WebDataBinderFactory();
        Parameter[] methodParams = handlerMethod.getMethod().getParameters();
        Object[] methodParamObjs = new Object[methodParams.length];
        int i = 0;
        for (Parameter p : methodParams) {
            Object methodParamObj = p.getType().newInstance();
            WebDataBinder wdb = binderFactory.createBinder(request,methodParamObj,p.getName());
            wdb.bind(request);
            methodParamObjs[i] = methodParamObj;
            i++;
        }
        Method invokeMethod = handlerMethod.getMethod();
        Object returnObj = invokeMethod.invoke(handlerMethod.getBean(),methodParamObjs);
        Class<?> returnType = invokeMethod.getReturnType();

        ModelAndView mav = null;
        if (invokeMethod.isAnnotationPresent(ResponseBody.class)) {
            this.messageConverter.write(returnObj,response);
        }else {
            if (returnObj instanceof ModelAndView){
                mav = (ModelAndView) returnObj;
            } else if (returnObj instanceof String) {
                String sTarget =  (String) returnObj;
                mav = new ModelAndView();
                mav.setViewName(sTarget);
            }
        }
        return mav;
    }

    public WebBindingInitializer getWebBindingInitializer() {
        return webBindingInitializer;
    }

    public void setWebBindingInitializer(WebBindingInitializer webBindingInitializer) {
        this.webBindingInitializer = webBindingInitializer;
    }
}
