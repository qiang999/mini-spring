package com.minis.jdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Title: RowMapper
 * @Package: com.minis.jdbc.core
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/8/24 - 16:25
 */
public interface RowMapper<T> {
    T mapRow(ResultSet rs,int rowNum) throws SQLException;
}