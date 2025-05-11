package com.minis.batis;

import com.minis.beans.factory.annotation.Autowired;
import com.minis.jdbc.core.JdbcTemplate;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Title: DefaultSqlSessionFactory
 * @Package: com.minis.batis
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/11 - 11:43
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    @Autowired
    JdbcTemplate jdbcTemplate;
    String mapperLocations;
    Map<String,MapperNode> mapperNodeMap = new HashMap<>();
    public Map<String,MapperNode> getMapperNodeMap() {
        return mapperNodeMap;
    }

    public DefaultSqlSessionFactory() {}

    public void init(){
        scanLocation(this.mapperLocations);
    }

    private void scanLocation(String location){
        String sLocationPath = this.getClass().getClassLoader().getResource("").getPath()+location;
        File dir = new File(sLocationPath);
        for(File file:dir.listFiles()){
            if(file.isDirectory()){
                scanLocation(location+"/"+file.getName());
            }else {
                buildMapperNodeMap(location+"/"+file.getName());
            }
        }
    }

    private Map<String,MapperNode> buildMapperNodeMap(String filePath){
        System.out.println(filePath);
        SAXReader saxReader = new SAXReader();
        URL xmlPath = this.getClass().getClassLoader().getResource(filePath);
        try {
            Document document = saxReader.read(xmlPath);
            Element root = document.getRootElement();

            String namespace = root.attributeValue("namespace");

            Iterator<Element> nodes = root.elementIterator();
            while(nodes.hasNext()){
                Element node = nodes.next();
                String id = node.attributeValue("id");
                String parameterType = node.attributeValue("parameterType");
                String resultType = node.attributeValue("resultType");
                String sql = node.getText();

                MapperNode selectNode = new MapperNode();
                selectNode.setNamespace(namespace);
                selectNode.setId(id);
                selectNode.setParameterType(parameterType);
                selectNode.setResultType(resultType);
                selectNode.setSql(sql);
                selectNode.setParameter("");
                this.mapperNodeMap.put(namespace+"."+id,selectNode);
            }
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        return this.mapperNodeMap;
    }

    @Override
    public SqlSession openSession() {
        SqlSession newSqlSession = new DefaultSqlSession();
        newSqlSession.setJdbcTemplate(jdbcTemplate);
        newSqlSession.setSqlSessionFactory(this);
        return newSqlSession;
    }

    @Override
    public MapperNode getMapperNode(String name) {
        return this.mapperNodeMap.get(name);
    }

    public String getMapperLocations() {
        return mapperLocations;
    }

    public void setMapperLocations(String mapperLocations) {
        this.mapperLocations = mapperLocations;
    }
}
