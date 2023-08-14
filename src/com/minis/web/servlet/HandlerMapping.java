package com.minis.web.servlet;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: HandlerMapping
 * @Package: com.minis.web.servlet
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/27 - 14:05
 */
public interface HandlerMapping {
    HandlerMethod getHandler(HttpServletRequest request) throws Exception;
}
