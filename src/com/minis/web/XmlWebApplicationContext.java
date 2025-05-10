package com.minis.web;

import com.minis.beans.factory.config.BeanFactoryPostProcessor;
import com.minis.context.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;

/**
 * @Title: XmlWebApplicationContext
 * @Package: com.minis.web
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/9 - 16:19
 */
public class XmlWebApplicationContext extends ClassPathXmlApplicationContext implements WebApplicationContext {
    private ServletContext servletContext;

    public XmlWebApplicationContext(String fileName){
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
