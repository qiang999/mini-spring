package com.minis.jdbc.datasource;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @Title: SingleConnectionDataSource
 * @Package: com.minis.jdbc.datasource
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/8/24 - 14:09
 */
public class SingleConnectionDataSource implements DataSource {
    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
        try {
            Class.forName(this.driverClassName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Properties getConnectionProperties() {
        return connectionProperties;
    }

    public void setConnectionProperties(Properties connectionProperties) {
        this.connectionProperties = connectionProperties;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private Properties connectionProperties;
    private Connection connection;

    public SingleConnectionDataSource(){}

    @Override
    public Connection getConnection() throws SQLException {
        return getConnectionFromDriver(username,password);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getConnectionFromDriver(username,password);
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    protected Connection getConnectionFromDriver(String username, String password) throws SQLException{
        Properties mergedProps = new Properties();
        Properties connProps = getConnectionProperties();
        if (connProps!=null){
            mergedProps.putAll(connProps);
        }
        if (username!=null){
            mergedProps.setProperty("user",username);
        }
        if (password!=null){
            mergedProps.setProperty("password",password);
        }
        this.connection = getConnectionFromDriverManager(getUrl(),mergedProps);
        return this.connection;
    }

    protected Connection getConnectionFromDriverManager(String url,Properties props) throws SQLException{
        return DriverManager.getConnection(url,props)
    }
}
