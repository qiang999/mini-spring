package com.minis.web;

/**
 * @Title: MappingValue
 * @Package: com.minis.web
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/24 - 13:34
 */
public class MappingValue {
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    String uri;

    public String getClz() {
        return clz;
    }

    public void setClz(String clz) {
        this.clz = clz;
    }

    String clz;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    String method;

    public MappingValue(String uri, String clz, String method){
        this.uri = uri;
        this.clz = clz;
        this.method = method;
    }
}
