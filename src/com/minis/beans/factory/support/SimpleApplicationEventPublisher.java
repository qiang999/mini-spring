package com.minis.beans.factory.support;

import com.minis.context.ApplicationEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: SimpleApplicationEventPublisher
 * @Package: com.minis.beans.factory.support
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/18 - 16:59
 */
public class SimpleApplicationEventPublisher implements ApplicationEventPublisher {
    List<ApplicationListener> listeners = new ArrayList<>();

    @Override
    public void publishEvent(ApplicationEvent event) {
        for (ApplicationListener listener:
             listeners) {
            listener.onApplicationEvent(event);
        }
    }

    @Override
    public void addApplicationListener(ApplicationListener listener) {
        this.listeners.add(listener);
    }
}
