package com.minis.jdbc.pool;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @Title: PooledDataSource
 * @Package: com.minis.jdbc.pool
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/11 - 10:56
 */
public class PooledDataSource implements DataSource {
    private List<PooledConnection> connections = null;
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private int initialSize = 2;
    private Properties connectionProperties;

    private void initPool(){
        this.connections = new ArrayList<>(initialSize);
        for(int i=0;i<initialSize;i++){
            try {
                Connection connection = DriverManager.getConnection(url,username,password);
                PooledConnection pooledConnection = new PooledConnection(connection,false);
                this.connections.add(pooledConnection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return getConnectionFromDriver(getUsername(),getPassword());
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getConnectionFromDriver(username,password);
    }

    protected Connection getConnectionFromDriver(String username, String password) throws SQLException {
        Properties mergedProps = new Properties();
        Properties connProps = getConnectionProperties();
        if (connProps != null) {
            mergedProps.putAll(connProps);
        }
        if (username != null) {
            mergedProps.setProperty("user", username);
        }
        if (password != null) {
            mergedProps.setProperty("password", password);
        }
        if (this.connections==null){
            initPool();
        }

        PooledConnection pooledConnection = getAvailableConnection();
        while (pooledConnection==null){
            pooledConnection = getAvailableConnection();
            if (pooledConnection==null){
                try {
                    TimeUnit.MILLISECONDS.sleep(30);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return pooledConnection;
    }

    private PooledConnection getAvailableConnection() throws SQLException {
        for (PooledConnection pooledConnection : this.connections) {
            if (!pooledConnection.isActive()){
                pooledConnection.setActive(true);
                return pooledConnection;
            }
        }
        return null;
    }

    protected Connection getConnectionFromDriverManager(String url,Properties properties) throws SQLException {
        return DriverManager.getConnection(url,properties);
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

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
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

    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public Properties getConnectionProperties() {
        return connectionProperties;
    }

    public void setConnectionProperties(Properties connectionProperties) {
        this.connectionProperties = connectionProperties;
    }
}
