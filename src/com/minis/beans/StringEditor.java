package com.minis.beans;

/**
 * @Title: StringEditor
 * @Package: com.minis.beans
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/8/16 - 17:24
 */
public class StringEditor implements PropertyEditor{
    private Class<String> strClass;
    private String strFormat;
    private boolean allowEmpty;
    private Object value;
    public StringEditor(Class<String> strClass, boolean allowEmpty) throws IllegalArgumentException{
        this(strClass,"",allowEmpty);
    }
    public StringEditor(Class<String> strClass, String strFormat, boolean allowEmpty) throws IllegalArgumentException{
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
    public Object getAsTest() {
        return value.toString();
    }
}
