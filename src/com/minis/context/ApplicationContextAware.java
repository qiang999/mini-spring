package com.minis.context;

import com.minis.beans.BeansException;

/**
 * @Title: ApplicationContextAware
 * @Package: com.minis.context
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/8 - 17:11
 */
public interface ApplicationContextAware {
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
