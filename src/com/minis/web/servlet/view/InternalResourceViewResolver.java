package com.minis.web.servlet.view;

import com.minis.web.servlet.View;
import com.minis.web.servlet.ViewResolver;

/**
 * @Title: InternalResourceViewResolver
 * @Package: com.minis.web.servlet.view
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/8/22 - 16:17
 */
public class InternalResourceViewResolver implements ViewResolver {
    public Class<?> getViewClas() {
        return viewClas;
    }

    public void setViewClas(Class<?> viewClas) {
        this.viewClas = viewClas;
    }

    public String getViewClassName() {
        return viewClassName;
    }

    public void setViewClassName(String viewClassName) {
        this.viewClassName = viewClassName;
        Class<?> clz = null;
        try {
            clz = Class.forName(viewClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setViewClas(clz);
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = (prefix!=null?prefix:"");
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = (suffix!=null?suffix:"");
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    private Class<?> viewClas = null;
    private String viewClassName = "";
    private String prefix = "";
    private String suffix = "";
    private String contentType;

    public InternalResourceViewResolver(){
        if (getViewClas()==null){
            setViewClas(JstlView.class);
        }
    }

    @Override
    public View resolveViewName(String viewName) throws Exception {
        return buildView(viewName);
    }

    protected View buildView(String viewName) throws Exception{
        Class<?> viewClass = getViewClas();
        View view = (View) viewClass.newInstance();
        view.setUrl(getPrefix()+viewName+getSuffix());
        String contentType = getContentType();
        view.setContentType(contentType);
        return view;
    }
}
