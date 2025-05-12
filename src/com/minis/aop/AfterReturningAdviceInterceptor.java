package com.minis.aop;

/**
 * @Title: AfterReturningAdviceInterceptor
 * @Package: com.minis.aop
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/12 - 15:43
 */
public class AfterReturningAdviceInterceptor implements MethodInterceptor,AfterAdvice{
    private final AfterReturningAdvice advice;

    public AfterReturningAdviceInterceptor(AfterReturningAdvice advice){
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object retVal = invocation.proceed();
        this.advice.afterReturning(retVal,invocation.getMethod(),invocation.getArguments(),invocation.getThis());
        return retVal;
    }
}
