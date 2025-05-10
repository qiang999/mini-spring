package com.minis.web;

import org.dom4j.Element;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: XmlConfigReader
 * @Package: com.minis.web
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/8 - 22:11
 */
public class XmlConfigReader {

    public XmlConfigReader() {}

    public Map<String,MappingValue> loadConfig(Resource res){
        Map<String,MappingValue> map = new HashMap<>();

        while (res.hasNext()) {
            Element element = (Element) res.next();
            String beanID = element.attributeValue("id");
            String beanClass = element.attributeValue("class");
            String beanMethod = element.attributeValue("value");

            map.put(beanID,new MappingValue(beanID,beanClass,beanMethod));
        }

        return map;
    }
}
