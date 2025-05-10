package com.minis.beans;

/**
 * @Title: StringEditor
 * @Package: com.minis.beans
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/10 - 16:29
 */
public class StringEditor implements PropertyEditor{
    private Class<String> strClass;
    private String strFormat;
    private boolean allowEmpty;
    private Object value;

    public StringEditor(Class<String> strClass, boolean allowEmpty) {
        this(strClass, "", allowEmpty);
    }

    public StringEditor(Class<String> strClass, String strFormat, boolean allowEmpty) {
        this.strClass = strClass;
        this.strFormat = strFormat;
        this.allowEmpty = allowEmpty;
    }

    @Override
    public void setAsText(String text) {
        setValue(text);
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public Object getAsText() {
        return value.toString();
    }
}
