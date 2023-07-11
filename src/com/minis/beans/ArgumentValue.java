package com.minis.beans;

/**
 * @Title: ArgumentValue
 * @Package: com.minis.beans
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/11 - 14:06
 */
public class ArgumentValue {
    private Object value;
    private String type;
    private String name;

    public ArgumentValue(Object value, String type){
        this.value = value;
        this.type = type;
    }
    public ArgumentValue(Object value, String type, String name){
        this.value = value;
        this.type = type;
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}