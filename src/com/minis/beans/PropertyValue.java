package com.minis.beans;

/**
 * @Title: PropertyValue
 * @Package: com.minis.beans
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/11 - 14:12
 */
public class PropertyValue {
    private final String type;
    private final String name;
    private final Object value;
    private final boolean isRef;
    public PropertyValue(String type, String name, Object value, boolean isRef){
        this.type = type;
        this.name = name;
        this.value = value;
        this.isRef = isRef;
    }

    public PropertyValue(String type, String name, Object value){
        this(type,name,value,false);
    }

    public PropertyValue(String name, Object value){
        this("",name,value,false);
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public boolean isRef() {
        return isRef;
    }
}
