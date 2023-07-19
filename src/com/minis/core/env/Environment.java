package com.minis.core.env;

/**
 * @Title: Environment
 * @Package: com.minis.core.env
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/18 - 15:53
 */
public interface Environment extends PropertyResolver {
    String[] getActiveProfiles();
    String[] getDefaultProfiles();
    boolean acceptsProfiles(String... profiles);
}
