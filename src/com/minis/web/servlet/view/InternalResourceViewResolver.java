package com.minis.web.servlet.view;

import com.minis.web.servlet.View;
import com.minis.web.servlet.ViewResolver;

/**
 * @Title: InternalResourceViewResolver
 * @Package: com.minis.web.servlet.view
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/10 - 21:03
 */
public class InternalResourceViewResolver implements ViewResolver {
    private Class<?> viewClass = null;
    private String viewClassName = "";
    private String prefix = "";
    private String suffix = "";
    private String contentType;

    public InternalResourceViewResolver(){
        if (getViewClass()==null){
            setViewClass(JstlView.class);
        }
    }

    @Override
    public View resolveViewName(String viewName) throws Exception {
        return null;
    }

    public Class<?> getViewClass() {
        return viewClass;
    }

    public void setViewClass(Class<?> viewClass) {
        this.viewClass = viewClass;
    }

    public String getViewClassName() {
        return viewClassName;
    }

    public void setViewClassName(String viewClassName) {
        this.viewClassName = viewClassName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
