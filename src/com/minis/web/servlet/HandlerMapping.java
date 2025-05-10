package com.minis.web.servlet;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: HandlerMapping
 * @Package: com.minis.web.servlet
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/9 - 16:37
 */
public interface HandlerMapping {
    HandlerMethod getHandler(HttpServletRequest request) throws Exception;
}
