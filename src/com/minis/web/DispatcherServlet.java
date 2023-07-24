package com.minis.web;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: DispatcherSevlet
 * @Package: com.minis.web
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/24 - 14:11
 */
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String sContextConfigLocation;
    private List<String> packageNames = new ArrayList<>();
    private Map<String, Object> controllerObjs = new HashMap<>();
    private List<String> controllerNames = new ArrayList<>();
    private Map<String,Class<?>> controllerClasses = new HashMap<>();

    private List<String> urlMappingNames = new ArrayList<>();
    private Map<String, Object> mappingObjs = new HashMap<>();
    private Map<String, Method> mappingMethods = new HashMap<>();

    public DispatcherServlet(){
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);

        sContextConfigLocation = config.getInitParameter("contextConfigLocation");
        URL xmlPath = null;
        try {
            xmlPath = this.getServletContext().getResource(sContextConfigLocation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.packageNames = XmlScanComponentHelper.getNodeValue(xmlPath);
        Refresh();
    }

    protected void Refresh(){
        initController();
        initMapping();
    }

    protected void initController(){
        this.controllerNames = scanPackages(this.packageNames);
        for (String controllerName:
             this.controllerNames) {
            Object obj = null;
            Class<?> clz = null;
            try {
                clz = Class.forName(controllerName);
                this.controllerClasses.put(controllerName, clz);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                obj = clz.newInstance();
                this.controllerObjs.put(controllerName, obj);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private List<String> scanPackages(List<String> packages){
        List<String> tempControllerNames = new ArrayList<>();
        for (String packageName:
             packages) {
            tempControllerNames.addAll(scanPackage(packageName));
        }
        return tempControllerNames;
    }

    private List<String> scanPackage(String packageName){
        List<String> tempControllerNames = new ArrayList<>();
        URI uri = null;
        try {
            uri = this.getClass().getClassLoader().getResource("/"+packageName.replaceAll("\\.","/")).toURI();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        File dir = new File(uri);
        for (File file:
             dir.listFiles()) {
            if (file.isDirectory()){
                scanPackage(packageName+"."+file.getName());
            }else {
                String controllerName = packageName+"."+file.getName().replace(".class","");
                tempControllerNames.add(controllerName);
            }
        }
        return tempControllerNames;
    }

    protected void initMapping(){
        for (String controllerName:
             this.controllerNames) {
            Class<?> clazz = this.controllerClasses.get(controllerName);
            Object obj = this.controllerObjs.get(controllerName);
            Method[] methods = clazz.getDeclaredMethods();
            if (methods!=null){
                for (Method method:
                     methods) {
                    boolean isRequestMapping = method.isAnnotationPresent(RequestMapping.class);
                    if (isRequestMapping){
                        String methodName = method.getName();
                        String urlMapping = method.getAnnotation(RequestMapping.class).value();
                        this.urlMappingNames.add(urlMapping);
                        this.mappingObjs.put(urlMapping,obj);
                        this.mappingMethods.put(urlMapping,method);
                    }
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String sPath = request.getServletPath();
        if (!this.urlMappingNames.contains(sPath)){
            return;
        }
        Object obj = null;
        Object objResult = null;
        try {
            Method method = this.mappingMethods.get(sPath);
            obj = this.mappingObjs.get(sPath);
            objResult = method.invoke(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.getWriter().append(objResult.toString());
    }
}
