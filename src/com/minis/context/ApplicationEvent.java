package com.minis.context;

import java.util.EventObject;

/**
 * @Title: ApplicationEvent
 * @Package: com.minis.context
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/11 - 11:22
 */
public class ApplicationEvent extends EventObject {
    private static final long serialVersionUID = 1L;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
