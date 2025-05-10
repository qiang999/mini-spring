package com.minis.web.servlet;

import java.util.Locale;

/**
 * @Title: ViewResolver
 * @Package: com.minis.web.servlet
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/10 - 20:57
 */
public interface ViewResolver {
    View resolveViewName(String viewName) throws Exception;
}
