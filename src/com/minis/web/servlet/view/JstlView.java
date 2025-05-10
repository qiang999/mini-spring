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
 * @Date: 2025/5/10 - 21:08
 */
public class JstlView implements View {
    public static final String DEFAULT_CONTENT_TYPE = "text/html;charset=ISO-8859-1";
    private String contentType = DEFAULT_CONTENT_TYPE;
    private String requestContextAttribute;
    private String beanName;
    private String url;

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        for(Map.Entry<String,?> e:model.entrySet()){
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

    @Override
    public String getContentType() {
        return this.contentType;
    }

    public String getBeanName() {
        return this.beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
