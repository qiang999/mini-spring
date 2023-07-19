package com.minis.beans.factory.support;

import com.minis.context.ApplicationEvent;

/**
 * @Title: ContextRefreshEvent
 * @Package: com.minis.beans.factory.support
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/18 - 16:56
 */
public class ContextRefreshEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshEvent(Object source) {
        super(source);
    }

    public String toString(){
        return this.msg;
    }
}
