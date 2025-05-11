package com.minis.jdbc.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Title: PreparedStatementCallback
 * @Package: com.minis.jdbc
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/10 - 22:58
 */
public interface PreparedStatementCallback {
    Object doInPreparedStatement(PreparedStatement ps) throws SQLException;
}
