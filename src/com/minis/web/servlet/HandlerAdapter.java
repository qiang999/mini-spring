package com.minis.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title: HandlerAdapter
 * @Package: com.minis.web.servlet
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/9 - 16:39
 */
public interface HandlerAdapter {
    ModelAndView handle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception;
}
