package com.minis.web;

import com.minis.context.ApplicationContext;

import javax.servlet.ServletContext;

/**
 * @Title: WebApplicationContext
 * @Package: com.minis.web
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/25 - 13:54
 */
public interface WebApplicationContext extends ApplicationContext {
    String ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE = WebApplicationContext.class.getName()+".ROOT";

    ServletContext getServletContext();
    void setServletContext(ServletContext servletContext);
}
