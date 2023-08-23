package com.minis.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Title: View
 * @Package: com.minis.web.servlet
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/8/22 - 15:54
 */
public interface View {
    void render(Map<String,?> model, HttpServletRequest request, HttpServletResponse response) throws Exception;
    default String getContentType(){
        return null;
    }
    void setContentType(String contentType);
    void setUrl(String url);
    String getUrl();
    void setRequestContextAttribute(String requestContextAttribute);
    String getRequestContextAttribute();
}
