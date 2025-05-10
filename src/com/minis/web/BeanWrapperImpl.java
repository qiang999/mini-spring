package com.minis.web;

import com.minis.beans.PropertyEditor;
import com.minis.beans.PropertyEditorRegistrySupport;
import com.minis.beans.PropertyValue;
import com.minis.beans.PropertyValues;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Title: BeanWrapperImpl
 * @Package: com.minis.web
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/10 - 16:53
 */
public class BeanWrapperImpl extends PropertyEditorRegistrySupport {
    Object wrappedObject;
    Class<?> clz;
    PropertyValues pvs;

    public BeanWrapperImpl(Object object) {
        registerDefaultEditor();
        this.wrappedObject = object;
        this.clz = object.getClass();
    }

    public void setBeanInstance(Object object) {
        this.wrappedObject = object;
    }

    public Object getBeanInstance() {
        return wrappedObject;
    }

    public void setPropertyValues(PropertyValues pvs) {
        this.pvs = pvs;
        for (PropertyValue pv : pvs.getPropertyValues()) {
            setPropertyValue(pv);
        }
    }

    public void setPropertyValue(PropertyValue pv) {
        BeanPropertyHandler propertyHandler = new BeanPropertyHandler(pv.getName());
        PropertyEditor pe = this.findCustomEditor(propertyHandler.getPropertyClz());
        if (pe==null) {
            pe = this.getDefaultEditor(propertyHandler.getPropertyClz());
        }
        pe.setAsText((String) pv.getValue());
        propertyHandler.setValue(pe.getValue());
    }

    class BeanPropertyHandler{
        Method writeMethod = null;
        Method readMethod = null;
        Class<?> propertyClz = null;
        public Class<?> getPropertyClz() {
            return propertyClz;
        }

        public BeanPropertyHandler(String propertyName) {
            try {
                Field field = clz.getDeclaredField(propertyName);
                propertyClz = field.getType();
                this.writeMethod = clz.getDeclaredMethod("set"+propertyName.substring(0,1).toUpperCase()+propertyName.substring(1),propertyClz);
                this.readMethod = clz.getDeclaredMethod("get"+propertyName.substring(0,1).toUpperCase()+propertyName.substring(1),propertyClz);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

        public Object getValue(){
            Object result = null;
            writeMethod.setAccessible(true);
            try {
                result = readMethod.invoke(wrappedObject);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            return result;
        }

        public void setValue(Object value){
            writeMethod.setAccessible(true);
            try {
                writeMethod.invoke(wrappedObject,value);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
