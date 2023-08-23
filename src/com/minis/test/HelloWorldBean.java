package com.minis.test;

import com.minis.test.entity.User;
import com.minis.web.RequestMapping;
import com.minis.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Title: HelloWorldBean
 * @Package: com.minis.test
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/24 - 15:37
 */
public class HelloWorldBean {
    @RequestMapping("/test7")
    @ResponseBody
    public User doTest7(User user){
        user.setName(user.getName()+"---");
        user.setBirthday(new Date());
        return user;
    }
}
