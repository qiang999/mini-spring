package com.minis.context;

import java.util.EventObject;

/**
 * @Title: ApplicationEvent
 * @Package: com.minis.context
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/7 - 17:06
 */
public class ApplicationEvent extends EventObject {

    private static final long serialVersionUID = 1L;
    protected String msg = null;
    public ApplicationEvent(Object source) {
        super(source);
        this.msg = source.toString();
    }

}
