package com.minis.web;

/**
 * @Title: ObjectMapper
 * @Package: com.minis.web
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/8/22 - 10:50
 */
public interface ObjectMapper {
    void setDateFormat(String dateFormat);
    void setDecimalFormat(String decimalFormat);
    String writeValuesAsString(Object obj) throws IllegalAccessException;
}
