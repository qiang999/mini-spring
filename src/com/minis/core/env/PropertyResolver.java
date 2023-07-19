package com.minis.core.env;

/**
 * @Title: PropertyResolver
 * @Package: com.minis.core.env
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/18 - 15:56
 */
public interface PropertyResolver {
    boolean containsProperty(String key);
    String getProperty(String key);
    String getProperty(String key, String defaultValue);
    <T> T getProperty(String key, Class<T> targetType);
    <T> T getProperty(String key, Class<T> targetType, T defaultValue);
    <T> Class<T> getPropertyAsClass(String key, Class<T> targetType);
    String getRequiredProperty(String key) throws IllegalStateException;
    <T> T getRequiredProperty(String key, Class<T> targetType) throws IllegalStateException;
    String resolvePlaceholders(String text);
    String resolveRequiredPlaceholders(String text) throws IllegalArgumentException;
}
