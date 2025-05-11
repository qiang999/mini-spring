package com.minis.batis;

import com.minis.jdbc.core.JdbcTemplate;
import com.minis.jdbc.core.PreparedStatementCallback;

/**
 * @Title: SqlSession
 * @Package: com.minis.batis
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/11 - 11:39
 */
public interface SqlSession {
    void setJdbcTemplate(JdbcTemplate jdbcTemplate);
    void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory);
    Object selectOne(String sqlid, Object[] args, PreparedStatementCallback pstmtcallback);
}
