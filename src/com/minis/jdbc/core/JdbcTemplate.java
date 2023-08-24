package com.minis.jdbc.core;

import javax.sql.DataSource;
import java.sql.*;

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
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                if (arg instanceof String){
                    pstmt.setString(i+1,(String) arg);
                }else if (arg instanceof Integer){
                    pstmt.setInt(i+1,(int)arg);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return preparedStatementCallback.doInPreparedStatement(pstmt);
    }
}
