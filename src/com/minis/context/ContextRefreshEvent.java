package com.minis.context;

/**
 * @Title: ContextRefreshEvent
 * @Package: com.minis.context
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/8 - 15:55
 */
public class ContextRefreshEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    public ContextRefreshEvent(Object source) {
        super(source);
    }

    public String toString() {
        return this.msg;
    }
}
