package com.minis.beans;

/**
 * @Title: BeanDefinition
 * @Package: com.minis.beans
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/9 - 16:35
 */
public class BeanDefinition {
    private String id;
    private String className;

    public BeanDefinition(String id, String className){
        this.id = id;
        this.className = className;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
