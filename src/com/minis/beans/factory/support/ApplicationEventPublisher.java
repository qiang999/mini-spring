package com.minis.beans.factory.support;

import com.minis.context.ApplicationEvent;

/**
 * @Title: ApplicationEventPublisher
 * @Package: com.minis.beans.factory.support
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/18 - 16:57
 */
public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
    void addApplicationListener(ApplicationListener listener);
}
