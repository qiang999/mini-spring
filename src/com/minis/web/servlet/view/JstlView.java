package com.minis.web.servlet.view;

import com.minis.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Title: JstlView
 * @Package: com.minis.web.servlet.view
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/8/22 - 16:39
 */
public class JstlView implements View {
    public static final String DEFAULT_CONTENT_TYPE = "text/html;charset=ISO-8859-1";

    @Override
    public String getContentType() {
        return contentType;
    }

    private String contentType = DEFAULT_CONTENT_TYPE;
    private String requestContextAttribute;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    private String beanName;
    private String url;

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        for (Map.Entry<String,?> e:
             model.entrySet()) {
            request.setAttribute(e.getKey(),e.getValue());
        }
        request.getRequestDispatcher(getUrl()).forward(request,response);
    }

    @Override
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public void setRequestContextAttribute(String requestContextAttribute) {
        this.requestContextAttribute = requestContextAttribute;
    }

    @Override
    public String getRequestContextAttribute() {
        return this.requestContextAttribute;
    }
}
