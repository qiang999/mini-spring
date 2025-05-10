package com.minis.web;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: WebDataBinderFactory
 * @Package: com.minis.web
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/10 - 17:19
 */
public class WebDataBinderFactory {
    public WebDataBinder createBinder(HttpServletRequest request,Object target,String objectName){
        WebDataBinder wbd = new WebDataBinder(target,objectName);
        initBinder(wbd,request);
        return wbd;
    }

    protected void initBinder(WebDataBinder dataBinder, HttpServletRequest request){}
}
