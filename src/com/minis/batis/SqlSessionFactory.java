package com.minis.batis;

/**
 * @Title: SqlSessionFactory
 * @Package: com.minis.batis
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/8/25 - 10:44
 */
public interface SqlSessionFactory {
    SqlSession openSession();
    MapperNode getMapperNode(String name);
}
