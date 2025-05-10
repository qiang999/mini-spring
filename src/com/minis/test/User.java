package com.minis.test;

import java.util.Date;

/**
 * @Title: User
 * @Package: com.minis.test
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/10 - 21:16
 */
public class User {
    int id = 1;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    String name = "";

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    Date birthday = new Date();

    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

}