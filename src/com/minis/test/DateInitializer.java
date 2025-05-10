package com.minis.test;

import com.minis.web.WebBindingInitializer;
import com.minis.web.WebDataBinder;

import java.util.Date;

/**
 * @Title: DateInitializer
 * @Package: com.minis.test
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/10 - 18:07
 */
public class DateInitializer implements WebBindingInitializer {
    @Override
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(Date.class,"yyyy-MM-dd",false));
    }
}
