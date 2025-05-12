package com.minis.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.Callable;

/**
 * @Title: JdkDynamicAopProxy
 * @Package: com.minis.aop
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/12 - 11:02
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {
    Object target;
    PointcutAdvisor advisor;

    public JdkDynamicAopProxy(Object target,PointcutAdvisor advisor) {
        this.target = target;
        this.advisor = this.advisor;
    }

    @Override
    public Object getProxy() {
        Object obj = Proxy.newProxyInstance(JdkDynamicAopProxy.class.getClassLoader(), target.getClass().getInterfaces(), this);
        return obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> targetClass = (target != null ? target.getClass() : null);
        if (this.advisor.getPointcut().getMethodMatcher().matches(method, targetClass)) {
            MethodInterceptor interceptor = this.advisor.getMethodInterceptor();
            MethodInvocation invocation = new ReflectiveMethodInvocation(proxy,target,method,args,targetClass);
            return interceptor.invoke(invocation);
        }
        return null;
    }
}
