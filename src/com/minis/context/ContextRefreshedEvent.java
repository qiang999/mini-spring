package com.minis.context;

/**
 * @Title: ContextRefreshedEvent
 * @Package: com.minis.context
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/8 - 17:17
 */
public class ContextRefreshedEvent extends ApplicationContextEvent{

    private static final long serialVersionUID = 1L;

    public ContextRefreshedEvent(ApplicationContext source) {
        super(source);
    }

    public String toString() {
        return this.msg;
    }

}
