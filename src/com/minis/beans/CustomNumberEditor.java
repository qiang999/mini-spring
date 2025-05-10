package com.minis.beans;

import com.minis.util.NumberUtils;
import com.minis.util.StringUtils;

import java.text.NumberFormat;

/**
 * @Title: CustomNumberEditor
 * @Package: com.minis.beans
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/9 - 22:02
 */
public class CustomNumberEditor implements PropertyEditor {
    private Class<? extends Number> numberClass;
    private NumberFormat numberFormat;
    private boolean allowEmpty;
    private Object value;

    public CustomNumberEditor(Class<? extends Number> numberClass,boolean allowEmpty){
        this(numberClass,null,allowEmpty);
    }

    public CustomNumberEditor(Class<? extends Number> numberClass, NumberFormat numberFormat, boolean allowEmpty) {
        this.numberClass = numberClass;
        this.numberFormat = numberFormat;
        this.allowEmpty = allowEmpty;
    }

    @Override
    public void setAsText(String text) {
        if (this.allowEmpty&& !StringUtils.hasText(text)) {
            setValue(null);
        } else if (this.numberFormat!=null) {
            setValue(NumberUtils.parseNumber(text, this.numberClass,this.numberFormat));
        }else {
            setValue(NumberUtils.parseNumber(text,this.numberClass));
        }
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof Number) {
            this.value = (NumberUtils.convertNumberToTargetClass((Number) value,this.numberClass));
        }else {
            this.value = value;
        }
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public Object getAsText() {
        Object value = this.value;
        if(value==null){
            return "";
        }
        if (this.numberFormat!=null){
            return this.numberFormat.format(value);
        }else  {
            return value.toString();
        }
    }
}
