package com.minis.test.entity;

import java.util.Date;

/**
 * @Title: User
 * @Package: com.minis.test.entity
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/8/22 - 14:46
 */
public class User {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    int id = 1;
    String name = "";
    Date birthday = new Date();
}
