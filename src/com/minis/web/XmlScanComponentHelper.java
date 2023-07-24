package com.minis.web;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Title: XmlScanComponentHelper
 * @Package: com.minis.web
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/24 - 14:56
 */
public class XmlScanComponentHelper {
    public static List<String> getNodeValue(URL xmlPath){
        List<String> packages = new ArrayList<>();
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(xmlPath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Element root = document.getRootElement();
        Iterator it = root.elementIterator();
        while (it.hasNext()){
            Element element = (Element) it.next();
            packages.add(element.attributeValue("base-package"));
        }
        return packages;
    }
}
