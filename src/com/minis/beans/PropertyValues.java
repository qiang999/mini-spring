package com.minis.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Title: PropertyValues
 * @Package: com.minis.beans
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/7 - 17:32
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValues;

    public PropertyValues() {
        this.propertyValues = new ArrayList<PropertyValue>(10);
    }
    public PropertyValues(Map<String, Object> map) {
        this.propertyValues = new ArrayList<PropertyValue>(10);
        for (Map.Entry<String,Object> e: map.entrySet()) {
            PropertyValue pv = new PropertyValue(e.getKey(),e.getValue());
            this.propertyValues.add(pv);
        }
    }

    public List<PropertyValue> getPropertyValueList() {
        return this.propertyValues;
    }

    public int size() {
        return this.propertyValues.size();
    }

    public void addPropertyValue(PropertyValue propertyValue) {
        this.propertyValues.add(propertyValue);
    }

//    public void addPropertyValue(String propertyType, String propertyName, Object propertyValue) {
//        addPropertyValue(new PropertyValue(propertyType,propertyName,propertyValue));
//    }

    public void removePropertyValue(PropertyValue propertyValue) {
        this.propertyValues.remove(propertyValue);
    }

    public void removePropertyValue(String propertyName) {
        this.propertyValues.remove(getPropertyValue(propertyName));
    }

    public PropertyValue[] getPropertyValues() {
        return this.propertyValues.toArray(new PropertyValue[this.propertyValues.size()]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue propertyValue : this.propertyValues) {
            if (propertyValue.getName().equals(propertyName)) {
                return propertyValue;
            }
        }
        return null;
    }

    public Object get(String propertyName) {
        PropertyValue propertyValue = getPropertyValue(propertyName);
        return propertyValue == null ? null : propertyValue.getValue();
    }

    public boolean contains(String propertyName) {
        return getPropertyValue(propertyName) != null;
    }

    public boolean isEmpty() {
        return this.propertyValues.isEmpty();
    }
}
