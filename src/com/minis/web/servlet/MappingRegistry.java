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
 * @Date: 2025/5/9 - 16:47
 */
public class MappingRegistry {
    private List<String> urlMappingNames = new ArrayList<String>();
    private Map<String,Object> mappingObjs = new HashMap<>();
    private Map<String, Method> mappingMethods = new HashMap<>();

    public void setUrlMappingNames(List<String> urlMappingNames) {
        this.urlMappingNames = urlMappingNames;
    }

    public List<String> getUrlMappingNames() {
        return urlMappingNames;
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
}
