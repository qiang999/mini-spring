package com.minis.jdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Title: ResultSetExtractor
 * @Package: com.minis.jdbc.core
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/11 - 10:36
 */
public interface ResultSetExtractor<T> {
    T extractData(ResultSet rs) throws SQLException;
}
