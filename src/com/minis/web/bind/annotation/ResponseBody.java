package com.minis.web.bind.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title: ResponseBody
 * @Package: com.minis.web.bind.annotation
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/8/22 - 14:05
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseBody {
}
