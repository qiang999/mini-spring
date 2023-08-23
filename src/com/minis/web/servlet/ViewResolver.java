package com.minis.web.servlet;

/**
 * @Title: ViewResolver
 * @Package: com.minis.web.servlet
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/8/22 - 16:14
 */
public interface ViewResolver {
    View resolveViewName(String viewName) throws Exception;
}
