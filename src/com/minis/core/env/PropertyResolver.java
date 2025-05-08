package com.minis.core.env;

/**
 * @Title: PropertyResolver
 * @Package: com.minis.core.env
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/8 - 15:30
 */
public interface PropertyResolver {
    boolean containsProperty(String key);
    String getProperty(String key);
    String getProperty(String key, String defaultValue);
    <T> T getProperty(String key, Class<T> type);
    <T> T getProperty(String key, Class<T> type, T defaultValue);
    <T> Class<T> getPropertyAsClass(String key, Class<T> type);
    String getRequiredProperty(String key) throws IllegalStateException;
    <T>  T getRequiredProperty(String key, Class<T> type) throws IllegalStateException;
    String resolvePlaceholders(String text);
    String resolveRequiredPlaceholders(String text) throws IllegalArgumentException;
}
