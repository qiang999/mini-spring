package com.minis.web;

import com.minis.beans.PropertyEditor;
import com.minis.beans.PropertyValues;
import com.minis.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Title: WebDataBinder
 * @Package: com.minis.web
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/9 - 21:46
 */
public class WebDataBinder {
    private Object target;
    private Class<?> clz;
    private String objectName;
    public WebDataBinder(Object target){
        this(target,"");
    }

    public WebDataBinder(Object target,String targetName){
        this.target = target;
        this.objectName = targetName;
        this.clz = this.target.getClass();
    }

    public void bind(HttpServletRequest request){
        PropertyValues mpvs = assignParameters(request);
        addBindValues(mpvs,request);
        doBind(mpvs);
    }

    private void doBind(PropertyValues mpvs){
        applyPropertyValues(mpvs);
    }

    protected void applyPropertyValues(PropertyValues mpvs){
        getPropertyAccessor().setPropertyValues(mpvs);
    }

    protected BeanWrapperImpl getPropertyAccessor(){
        return new BeanWrapperImpl(this.target);
    }

    private PropertyValues assignParameters(HttpServletRequest request){
        Map<String,Object> map = WebUtils.getParametersStartingWith(request,"");
        return new PropertyValues(map);
    }

    protected void addBindValues(PropertyValues mpvs,HttpServletRequest request){}

    public void registerCustomEditor(Class<?> requiredType, PropertyEditor propertyEditor){
        getPropertyAccessor().registerCustomEditor(requiredType,propertyEditor);
    }
}
