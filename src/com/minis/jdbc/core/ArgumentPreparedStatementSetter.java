package com.minis.jdbc.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * @Title: ArgumentPreparedStatementSetter
 * @Package: com.minis.jdbc.core
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/8/24 - 16:09
 */
public class ArgumentPreparedStatementSetter {
    private final Object[] args;

    public ArgumentPreparedStatementSetter(Object[] args){
        this.args = args;
    }

    public void setValues(PreparedStatement preparedStatement) throws SQLException{
        if (this.args!=null){
            for (int i = 0; i < this.args.length; i++) {
                Object arg = this.args[i];
                doSetValue(preparedStatement,i+1,arg);
            }
        }
    }

    protected void doSetValue(PreparedStatement preparedStatement, int parameterPosition, Object argValue) throws SQLException{
        Object arg = argValue;
        if (arg instanceof String){
            preparedStatement.setString(parameterPosition,(String) arg);
        } else if (arg instanceof Integer) {
            preparedStatement.setInt(parameterPosition,(int)arg);
        } else if (arg instanceof Date) {
            preparedStatement.setDate(parameterPosition,new java.sql.Date(((Date)arg).getTime()));
        }
    }
}
