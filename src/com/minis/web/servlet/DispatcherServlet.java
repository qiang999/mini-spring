package com.minis.web.servlet;

import com.minis.beans.BeansException;
import com.minis.web.AnnotationConfigWebApplicationContext;
import com.minis.web.WebApplicationContext;
import com.minis.web.XmlScanComponentHelper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.util.*;

/**
 * @Title: DispatcherServlet
 * @Package: com.minis.web
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/8 - 22:26
 */
public class DispatcherServlet extends HttpServlet {

    public static final String WEB_APPLICATION_CONTEXT_ATTRIBUTE = DispatcherServlet.class.getName() + ".CONTEXT";
    private WebApplicationContext webApplicationContext;
    private WebApplicationContext parentWebApplicationContext;
    private String sContextConfigLocation;
    private List<String> packageNames = new ArrayList<>();
    private Map<String, Object> controllerObjs = new HashMap<>();
    private List<String> controllerNames = new ArrayList<>();
    private Map<String,Class<?>> controllerClasses = new HashMap<>();
    private HandlerMapping handlerMapping;
    private HandlerAdapter handlerAdapter;
    public static final String HANDLER_ADAPTER_BEAN_NAME = "handlerAdapter";
    private ViewResolver viewResolver;

    public DispatcherServlet(){
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.parentWebApplicationContext = (WebApplicationContext) this.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        sContextConfigLocation = config.getInitParameter("contextConfigLocation");
        URL xmlPath = null;
        try {
            xmlPath = this.getServletContext().getResource(sContextConfigLocation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.packageNames = XmlScanComponentHelper.getNodeValue(xmlPath);
        this.webApplicationContext = new AnnotationConfigWebApplicationContext(sContextConfigLocation,this.parentWebApplicationContext);
        Refresh();
    }

    protected void Refresh(){
        initController();
        initHandlerMappings(this.webApplicationContext);
        initHandlerAdapters(this.webApplicationContext);
        initViewResolvers(this.webApplicationContext);
    }

    protected void initHandlerMappings(WebApplicationContext wac){
        this.handlerMapping = new RequestMappingHandlerMapping(wac);
    }

    protected void initHandlerAdapters(WebApplicationContext wac){
        try {
            this.handlerAdapter = (HandlerAdapter) wac.getBean(HANDLER_ADAPTER_BEAN_NAME);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void initViewResolvers(WebApplicationContext wac){}

    protected void initController(){
        this.controllerNames = Arrays.asList(this.webApplicationContext.getBeanDefinitionNames());
        for(String controllerName:this.controllerNames){
            Class<?> clz = null;
            try {
                clz = Class.forName(controllerName);
                this.controllerClasses.put(controllerName, clz);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                this.controllerObjs.put(controllerName, this.webApplicationContext.getBean(controllerName));
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp){
        req.setAttribute(WEB_APPLICATION_CONTEXT_ATTRIBUTE,this.webApplicationContext);
        try {
            doDispatch(req,resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {}
    }

    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpServletRequest httpRequest = request;
        HandlerMethod handlerMethod = null;
        ModelAndView mv = null;
        handlerMethod = this.handlerMapping.getHandler(httpRequest);
        if (handlerMethod == null) {
            return;
        }
        HandlerAdapter ha = this.handlerAdapter;
        mv = ha.handle(httpRequest, response, handlerMethod);
        render(request, response, mv);
    }

    protected void render(HttpServletRequest request, HttpServletResponse response,ModelAndView mv) throws Exception {
        String sTarget = mv.getViewName();
        Map<String, Object> model = mv.getModel();
        View view = resolveViewName(sTarget,model,request);
        view.render(model,request,response);
    }

    protected View resolveViewName(String viewName, Map<String, Object> model,
                                   HttpServletRequest request) throws Exception {
        if (this.viewResolver != null) {
            View view = viewResolver.resolveViewName(viewName);
            if (view != null) {
                return view;
            }
        }
        return null;
    }
}
