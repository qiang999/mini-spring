package com.minis.beans;

/**
 * @Title: PropertyValue
 * @Package: com.minis.beans
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/11 - 14:12
 */
public class PropertyValue {
    private final String name;
    private final Object value;
    public PropertyValue(String name, Object value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
