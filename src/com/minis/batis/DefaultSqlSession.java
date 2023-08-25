package com.minis.batis;

import com.minis.jdbc.core.JdbcTemplate;
import com.minis.jdbc.core.PreparedStatementCallback;

import java.sql.SQLException;

/**
 * @Title: DefaultSqlSession
 * @Package: com.minis.batis
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/8/25 - 11:20
 */
public class DefaultSqlSession implements SqlSession{
    JdbcTemplate jdbcTemplate;
    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public JdbcTemplate getJdbcTemplate(){
        return this.jdbcTemplate;
    }

    SqlSessionFactory sqlSessionFactory;
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
    public SqlSessionFactory getSqlSessionFactory(){
        return this.sqlSessionFactory;
    }

    @Override
    public Object selectOne(String sqlId, Object[] args, PreparedStatementCallback preparedStatementCallback) throws SQLException {
        System.out.println(sqlId);
        String sql = this.sqlSessionFactory.getMapperNode(sqlId).getSql();
        System.out.println(sql);
        return jdbcTemplate.query(sql,args,preparedStatementCallback);
    }

    private void buildParameter(){}

    private Object resultSet2Obj(){
        return null;
    }
}
