package com.minis.web;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: WebDataBinderFactory
 * @Package: com.minis.web
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/8/17 - 15:05
 */
public class WebDataBinderFactory {
    public WebDataBinder createBinder(HttpServletRequest request, Object target, String objectName){
        WebDataBinder wbd = new WebDataBinder(target, objectName);
        initBinder(wbd, request);
        return wbd;
    }

    protected void initBinder(WebDataBinder dataBinder,HttpServletRequest request){}
}
