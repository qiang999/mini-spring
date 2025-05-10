package com.minis.util;

/**
 * @Title: ObjectMapper
 * @Package: com.minis.util
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/10 - 18:50
 */
public interface ObjectMapper {
    void setDateFormat(String dateFormat);
    void setDecimalFormat(String decimalFormat);
    String writeValuesAsString(Object obj);
}
