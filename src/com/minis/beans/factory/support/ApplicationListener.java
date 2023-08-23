package com.minis.beans.factory.support;

import com.minis.context.ApplicationEvent;

import java.util.EventListener;

/**
 * @Title: ApplicationListener
 * @Package: com.minis.beans.factory.support
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/18 - 16:54
 */
public class ApplicationListener<E extends ApplicationEvent> implements EventListener {
    void onApplicationEvent(ApplicationEvent event){
        System.out.println(event.toString());
    }
}
