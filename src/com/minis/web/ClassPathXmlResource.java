package com.minis.web;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.Iterator;


/**
 * @Title: ClassPathXmlResource
 * @Package: com.minis.web
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/8 - 22:05
 */
public class ClassPathXmlResource implements Resource {
    Document document;
    Element rootElement;
    Iterator<Element> elementIterator;

    public ClassPathXmlResource(URL xmlPath) {
        SAXReader reader = new SAXReader();
        try {
            this.document = reader.read(xmlPath);
            this.rootElement = this.document.getRootElement();
            this.elementIterator = this.rootElement.elementIterator();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean hasNext() {
        return this.elementIterator.hasNext();
    }

    @Override
    public Object next() {
        return this.elementIterator.next();
    }
}
