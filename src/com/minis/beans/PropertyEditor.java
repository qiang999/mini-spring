package com.minis.beans;

/**
 * @Title: PropertyEditor
 * @Package: com.minis.beans
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/8/16 - 17:06
 */
public interface PropertyEditor {
    void setAsText(String text);
    void setValue(Object value);
    Object getValue();
    Object getAsTest();
}
