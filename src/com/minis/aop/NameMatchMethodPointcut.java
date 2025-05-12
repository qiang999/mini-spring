package com.minis.aop;

import com.minis.util.PatternMatchUtils;

import java.lang.reflect.Method;

/**
 * @Title: NameMatchMethodPointcut
 * @Package: com.minis.aop
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/12 - 15:53
 */
public class NameMatchMethodPointcut implements MethodMatcher,Pointcut {
    private String mappedName = "";

    public void setMappedName(String mappedName) {
        this.mappedName = mappedName;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        if (mappedName.equals(method.getName())||isMatch(method.getName(),mappedName)) {
            return true;
        }
        return false;
    }

    protected boolean isMatch(String method, String mappedName) {
        return PatternMatchUtils.simpleMatch(mappedName, method);
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return null;
    }
}
