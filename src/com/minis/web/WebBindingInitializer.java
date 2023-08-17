package com.minis.web;

/**
 * @Title: WebBindingInitializer
 * @Package: com.minis.web
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/8/17 - 15:48
 */
public interface WebBindingInitializer {
    void initBinder(WebDataBinder binder);
}
