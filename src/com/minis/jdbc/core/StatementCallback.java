package com.minis.jdbc.core;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Title: StatementCallback
 * @Package: com.minis.jdbc.core
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/8/24 - 11:27
 */
public interface StatementCallback {
    Object doInStatement(Statement stmt) throws SQLException;
}
