package com.minis.beans;

import com.minis.core.Resource;
import org.dom4j.Element;

/**
 * @Title: XmlBeanDefinitionReader
 * @Package: com.minis.beans
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/10 - 14:48
 */
public class XmlBeanDefinitionReader {
    SimpleBeanFactory simpleBeanFactory;

    public XmlBeanDefinitionReader(SimpleBeanFactory simpleBeanFactory){
        this.simpleBeanFactory = simpleBeanFactory;
    }

    public void loadBeanDefinitions(Resource resource){
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanID = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);
            this.simpleBeanFactory.registerBeanDefinition(beanID, beanDefinition);
        }
    }
}
