package com.minis.context;

import com.minis.beans.*;
import com.minis.core.ClassPathXmlResource;
import com.minis.core.Resource;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.*;

/**
 * @Title: ClassPathXmlApplicationContext
 * @Package: com.minis.context
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/9 - 16:41
 */
public class ClassPathXmlApplicationContext implements BeanFactory {
    BeanFactory beanFactory;
    public ClassPathXmlApplicationContext(String fileName){
        Resource resource = new ClassPathXmlResource(fileName);
        BeanFactory beanFactory = new SimpleBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        this.beanFactory = beanFactory;
    }
    @Override
    public Object getBean(String beanName) throws BeansException {
        return this.beanFactory.getBean(beanName);
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanFactory.registerBeanDefinition(beanDefinition);
    }
//    private List<BeanDefinition> beanDefinitions = new ArrayList<>();
//    private Map<String, Object> singletons = new HashMap<>();
//    public ClassPathXmlApplicationContext(String fileName){
//        this.readXml(fileName);
//        this.instanceBeans();
//    }
//
//    private void readXml(String fileName){
//        SAXReader saxReader = new SAXReader();
//        try {
//            URL xmlPath = this.getClass().getClassLoader().getResource(fileName);
//            Document document = saxReader.read(xmlPath);
//            Element rootElement = document.getRootElement();
//            for (Element element:
//                    (List<Element>)rootElement.elements()) {
//                String beanID = element.attributeValue("id");
//                String beanClassName = element.attributeValue("class");
//                BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);
//                beanDefinitions.add(beanDefinition);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private void instanceBeans(){
//        for (BeanDefinition beanDefinition:
//             beanDefinitions) {
//            try {
//                singletons.put(beanDefinition.getId(), Class.forName(beanDefinition.getClassName()).newInstance());
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//    public Object getBean(String beanName){
//        return singletons.get(beanName);
//    }
}