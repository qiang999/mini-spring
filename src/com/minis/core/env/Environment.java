package com.minis.core.env;

/**
 * @Title: Environment
 * @Package: com.minis.core.env
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/8 - 15:28
 */
public interface Environment extends PropertyResolver {
    String[] getActiveProfiles();
    String[] getDefaultProfiles();
    boolean acceptsProfiles(String... profiles);
}
