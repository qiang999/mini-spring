package com.minis.beans.factory.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title: Autowired
 * @Package: com.minis.beans.factory.annotation
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/8 - 9:43
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
}
