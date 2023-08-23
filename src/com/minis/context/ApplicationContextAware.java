package com.minis.context;

import com.minis.beans.BeansException;

/**
 * @Title: ApplicationContextAware
 * @Package: com.minis.context
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/8/22 - 15:33
 */
public interface ApplicationContextAware {
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
