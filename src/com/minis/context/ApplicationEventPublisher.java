package com.minis.context;

import com.minis.beans.factory.support.ApplicationListener;

/**
 * @Title: ApplicationEventPublisher
 * @Package: com.minis.context
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/11 - 11:20
 */
public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
    void addApplicationListener(ApplicationListener listener);
}
