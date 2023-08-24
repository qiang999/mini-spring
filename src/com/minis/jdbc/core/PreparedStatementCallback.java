package com.minis.jdbc.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Title: PreparedStatementCallback
 * @Package: com.minis.jdbc.core
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/8/24 - 13:50
 */
public interface PreparedStatementCallback {
    Object doInPreparedStatement(PreparedStatement statement) throws SQLException;
}
