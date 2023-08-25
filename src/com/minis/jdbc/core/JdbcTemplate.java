package com.minis.jdbc.core;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * @Title: JdbcTemplate
 * @Package: com.minis.jdbc.core
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/8/24 - 11:11
 */
public abstract class JdbcTemplate {
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private DataSource dataSource;
    public JdbcTemplate(){}

    public Object query(StatementCallback statementCallback){
        Connection con = null;
        Statement stmt = null;
        try {
            con = dataSource.getConnection();
            stmt = con.createStatement();
            return statementCallback.doInStatement(stmt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Object query(String sql,Object[] args,PreparedStatementCallback preparedStatementCallback) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql);
            ArgumentPreparedStatementSetter statementSetter = new ArgumentPreparedStatementSetter(args);
            statementSetter.setValues(pstmt);
            return preparedStatementCallback.doInPreparedStatement(pstmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            pstmt.close();
            con.close();
        }
    }

    public <T> List<T> query(String sql,Object[] args,RowMapper<T> rowMapper){
        RowMapperResultSetExtractor<T> resultSetExtractor = new RowMapperResultSetExtractor<>(rowMapper);
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql);
            ArgumentPreparedStatementSetter statementSetter = new ArgumentPreparedStatementSetter(args);
            statementSetter.setValues(pstmt);
            rs = pstmt.executeQuery();
            return resultSetExtractor.extractData(rs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {
                pstmt.close();
                con.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
