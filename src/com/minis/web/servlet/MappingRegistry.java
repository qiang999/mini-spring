package com.minis.web.servlet;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: MappingRegistry
 * @Package: com.minis.web.servlet
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/27 - 14:14
 */
public class MappingRegistry {
    public List<String> getUrlMappingNames() {
        return urlMappingNames;
    }

    public void setUrlMappingNames(List<String> urlMappingNames) {
        this.urlMappingNames = urlMappingNames;
    }

    public Map<String, Object> getMappingObjs() {
        return mappingObjs;
    }

    public void setMappingObjs(Map<String, Object> mappingObjs) {
        this.mappingObjs = mappingObjs;
    }

    public Map<String, Method> getMappingMethods() {
        return mappingMethods;
    }

    public void setMappingMethods(Map<String, Method> mappingMethods) {
        this.mappingMethods = mappingMethods;
    }

    public Map<String, String> getMappingMethodNames() {
        return mappingMethodNames;
    }

    public void setMappingMethodNames(Map<String, String> mappingMethodNames) {
        this.mappingMethodNames = mappingMethodNames;
    }

    public Map<String, Class<?>> getMappingClasses() {
        return mappingClasses;
    }

    public void setMappingClasses(Map<String, Class<?>> mappingClasses) {
        this.mappingClasses = mappingClasses;
    }

    private List<String> urlMappingNames = new ArrayList<>();
    private Map<String,Object> mappingObjs = new HashMap<>();
    private Map<String,Method> mappingMethods = new HashMap<>();
    private Map<String,String> mappingMethodNames = new HashMap<>();
    private Map<String,Class<?>> mappingClasses = new HashMap<>();
}
