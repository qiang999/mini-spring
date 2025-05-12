package com.minis.aop;

import java.lang.reflect.Method;

/**
 * @Title: ReflectiveMethodInvocation
 * @Package: com.minis.aop
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/12 - 11:38
 */
public class ReflectiveMethodInvocation implements MethodInvocation {
    protected final Object proxy;
    protected final Object target;
    protected final Method method;
    protected Object[] arguments;
    private Class<?> targetClass;

    protected ReflectiveMethodInvocation(Object proxy, Object target, Method method, Object[] arguments, Class<?> targetClass) {
        this.proxy = proxy;
        this.target = target;
        this.method = method;
        this.arguments = arguments;
        this.targetClass = targetClass;
    }

    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public Object[] getArguments() {
        return this.arguments;
    }

    @Override
    public Object getThis() {
        return this;
    }

    @Override
    public Object proceed() throws Throwable {
        return this.method.invoke(this.proxy, this.arguments);
    }
}
