package com.minis.batis;

/**
 * @Title: SqlSessionFactory
 * @Package: com.minis.batis
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/11 - 11:38
 */
public interface SqlSessionFactory {
    SqlSession openSession();
    MapperNode getMapperNode(String name);
}
