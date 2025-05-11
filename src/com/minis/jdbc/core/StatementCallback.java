package com.minis.jdbc.core;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Title: StatementCallback
 * @Package: com.minis.jdbc
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/10 - 22:57
 */
public interface StatementCallback {
    Object doInStatement(Statement ps) throws SQLException;
}
