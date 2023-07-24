package com.minis.test;

import com.minis.web.RequestMapping;

/**
 * @Title: HelloWorldBean
 * @Package: com.minis.test
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/24 - 15:37
 */
public class HelloWorldBean {
    @RequestMapping("/test")
    public String doTest(){
        return "hello world for doGet!";
    }
}
