package com.minis.web;

import com.minis.beans.BeansException;
import com.minis.web.servlet.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.util.*;

/**
 * @Title: DispatcherSevlet
 * @Package: com.minis.web
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/24 - 14:11
 */
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String WEB_APPLICATION_CONTEXT_ATTRIBUTE = DispatcherServlet.class.getName() + ".CONTEXT";
    public static final String HANDLER_MAPPING_BEAN_NAME = "handlerMapping";
    public static final String HANDLER_ADAPTER_BEAN_NAME = "handlerAdapter";
    public static final String MULTIPART_RESOLVER_BEAN_NAME = "multipartResolver";
    public static final String LOCALE_RESOLVER_BEAN_NAME = "localeResolver";
    public static final String HANDLER_EXCEPTION_RESOLVER_BEAN_NAME = "handlerExceptionResolver";
    public static final String REQUEST_TO_VIEW_NAME_TRANSLATOR_BEAN_NAME = "viewNameTranslator";
    public static final String VIEW_RESOLVER_BEAN_NAME = "viewResolver";
    private static final String DEFAULT_STRATEGIES_PATH = "DispatcherServlet.properties";
    private static final Properties defaultStrategies = null;
    private WebApplicationContext webApplicationContext;
    private WebApplicationContext parentApplicationContext;
    private String sContextConfigLocation;
    private HandlerMapping handlerMapping;
    private HandlerAdapter handlerAdapter;
    private ViewResolver viewResolver;

    public DispatcherServlet(){
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        this.parentApplicationContext = (WebApplicationContext) this.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        sContextConfigLocation = config.getInitParameter("contextConfigLocation");
        this.webApplicationContext = new AnnotationConfigWebApplicationContext(sContextConfigLocation, this.parentApplicationContext);
        try {
            Refresh();
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
    }

    protected void Refresh() throws BeansException {
        initHandlerMappings(this.webApplicationContext);
        initHandlerAdapters(this.webApplicationContext);
        initViewResolvers(this.webApplicationContext);
    }

    protected void initHandlerMappings(WebApplicationContext wac){
        try {
            this.handlerMapping = (HandlerMapping) wac.getBean(HANDLER_MAPPING_BEAN_NAME);
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
    }

    protected void initHandlerAdapters(WebApplicationContext wac) throws BeansException {
        this.handlerAdapter = (HandlerAdapter) wac.getBean(HANDLER_ADAPTER_BEAN_NAME);
    }

    protected void initViewResolvers(WebApplicationContext wac){
        try {
            this.viewResolver = (ViewResolver) wac.getBean(VIEW_RESOLVER_BEAN_NAME);
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
    }

    protected void service(HttpServletRequest request, HttpServletResponse response){
        request.setAttribute(WEB_APPLICATION_CONTEXT_ATTRIBUTE, this.webApplicationContext);
        try {
            doDispatch(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpServletRequest processedRequest = request;
        HandlerMethod handlerMethod = null;
        ModelAndView mv = null;
        handlerMethod = this.handlerMapping.getHandler(processedRequest);
        if (handlerMethod==null){
            return;
        }
        HandlerAdapter ha = this.handlerAdapter;
        mv = ha.handle(processedRequest, response, handlerMethod);
        render(processedRequest,response,mv);
    }

    protected void render(HttpServletRequest request,HttpServletResponse response,ModelAndView mv) throws Exception{
        if (mv==null){
            response.getWriter().flush();
            response.getWriter().close();
            return;
        }
        String sTarget = mv.getViewName();
        Map<String,Object> modelMap = mv.getModel();
        View view = resolveViewName(sTarget,modelMap,request);
        view.render(modelMap,request,response);
    }

    protected View resolveViewName(String viewName,Map<String,Object> model,HttpServletRequest request) throws Exception{
        if (this.viewResolver!=null){
            View view = viewResolver.resolveViewName(viewName);
            if (view!=null){
                return view;
            }
        }
        return null;
    }
}
