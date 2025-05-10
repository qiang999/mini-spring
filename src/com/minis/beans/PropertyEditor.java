package com.minis.beans;

/**
 * @Title: PropertyEditor
 * @Package: com.minis.beans
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/9 - 21:58
 */
public interface PropertyEditor {
    void setAsText(String text);
    void setValue(Object value);
    Object getValue();
    Object getAsText();
}
