package com.minis.web;

import com.minis.context.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;

/**
 * @Title: AnnotationConfigWebApplicationContext
 * @Package: com.minis.web
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/25 - 14:02
 */
public class AnnotationConfigWebApplicationContext extends ClassPathXmlApplicationContext implements WebApplicationContext {
    private ServletContext servletContext;

    public AnnotationConfigWebApplicationContext(String fileName){
        super(fileName);
    }

    @Override
    public ServletContext getServletContext() {
        return this.servletContext;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
