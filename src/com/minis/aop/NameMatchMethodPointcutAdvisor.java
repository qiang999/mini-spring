package com.minis.aop;

/**
 * @Title: NameMatchMethodPointcutAdvisor
 * @Package: com.minis.aop
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/12 - 16:01
 */
public class NameMatchMethodPointcutAdvisor implements PointcutAdvisor {
    private Advice advice = null;
    private MethodInterceptor methodInterceptor;
    private String meppedName;
    private final NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();

    public NameMatchMethodPointcutAdvisor() {}

    public NameMatchMethodPointcutAdvisor(Advice advice) {
        this.advice = advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
        MethodInterceptor mi = null;
        if (advice instanceof BeforeAdvice) {
            mi = new MethodBeforeAdviceInterceptor((MethodBeforeAdvice) advice);
        }else if (advice instanceof AfterAdvice) {
            mi = new AfterReturningAdviceInterceptor((AfterReturningAdvice) advice);
        }else if (advice instanceof MethodInterceptor){
            mi = (MethodInterceptor) advice;
        }
        setMethodInterceptor(mi);
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public MethodInterceptor getMethodInterceptor() {
        return this.methodInterceptor;
    }

    @Override
    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }
}
