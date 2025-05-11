package com.minis.jdbc.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Title: ArgumentPreparedStatementSetter
 * @Package: com.minis.jdbc.core
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/11 - 10:26
 */
public class ArgumentPreparedStatementSetter {
    private final Object[] args;

    public ArgumentPreparedStatementSetter(Object[] args) {
        this.args = args;
    }

    public void setValues(PreparedStatement pstmt) throws SQLException {
        if (this.args != null) {
            for (int i = 0; i < args.length; i++) {
                Object arg = this.args[i];
                doSetValue(pstmt,i+1,arg);
            }
        }
    }

    protected  void doSetValue(PreparedStatement pstmt, int index, Object argValue) throws SQLException {
        Object arg = argValue;
        if (arg instanceof String) {
            pstmt.setString(index, (String) arg);
        }else if (arg instanceof Integer) {
            pstmt.setInt(index, (int) arg);
        }else if (arg instanceof java.util.Date) {
            pstmt.setDate(index, new java.sql.Date(((java.util.Date) arg).getTime()));
        }
    }
}
