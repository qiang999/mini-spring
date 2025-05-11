package com.minis.batis;

import com.minis.jdbc.core.JdbcTemplate;
import com.minis.jdbc.core.PreparedStatementCallback;

/**
 * @Title: DefaultSqlSession
 * @Package: com.minis.batis
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/11 - 11:57
 */
public class DefaultSqlSession implements SqlSession{
    JdbcTemplate jdbcTemplate;
    SqlSessionFactory sqlSessionFactory;

    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return this.sqlSessionFactory;
    }

    @Override
    public Object selectOne(String sqlid, Object[] args, PreparedStatementCallback pstmtcallback) {
        String sql = this.sqlSessionFactory.getMapperNode(sqlid).getSql();
        return jdbcTemplate.query(sql, args, pstmtcallback);
    }
}
