package com.minis.test;

import com.minis.beans.PropertyEditor;
import com.minis.util.StringUtils;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Title: CustomDateEditor
 * @Package: com.minis.test
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/10 - 17:56
 */
public class CustomDateEditor implements PropertyEditor {
    private Class<Date> dateClass;
    private DateTimeFormatter dateTimeFormatter;
    private boolean allowEmpty;
    private Date value;

    public CustomDateEditor(){
        this(Date.class,"yyyy-MM-dd",true);
    }

    public CustomDateEditor(Class<Date> dateClass){
        this(dateClass,"yyyy-MM-dd",true);
    }

    public CustomDateEditor(Class<Date> dateClass,String pattern,boolean allowEmpty){
        this.dateClass = dateClass;
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        this.allowEmpty = allowEmpty;
    }

    @Override
    public void setValue(Object value) {
        this.value = (Date) value;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public String getAsText() {
        Date value = this.value;
        if (value == null){
            return "";
        }else {
            LocalDate localDate = value.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return localDate.format(dateTimeFormatter);
        }
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty&& !StringUtils.hasText(text)) {
            setValue(null);
        }else {
            LocalDate localDate = LocalDate.parse(text, dateTimeFormatter);
            setValue(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
    }
}
