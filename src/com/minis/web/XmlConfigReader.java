package com.minis.web;

import com.minis.core.Resource;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: XmlConfigReader
 * @Package: com.minis.web
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/24 - 13:53
 */
public class XmlConfigReader {
    public XmlConfigReader(){}

    public Map<String, MappingValue> loadConfig(Resource res){
        Map<String, MappingValue> mappings = new HashMap<>();

        while (res.hasNext()){
            Element element = (Element) res.next();
            String beanID = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            String beanMethod = element.attributeValue("value");

            mappings.put(beanID, new MappingValue(beanID,beanClassName,beanMethod));
        }

        return mappings;
    }
}
