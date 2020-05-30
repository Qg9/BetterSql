package net.zey.bettersql.database;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariDatabase extends Database {

    private HikariDataSource source;
    private String url;

    public HikariDatabase(String name, String user, String password, String host, int port, int maxPoolSize){
        super(name);
        source = new HikariDataSource();
        url = "jdbc:mysql://" + host + ":" + port + "/" + getName();
        source.setUsername(user);
        source.setPassword(password);
        source.setMaximumPoolSize(maxPoolSize);
    }

    @Override
    public String getURL() {
        return url;
    }

    @Override
    public void connect() {
        source.setJdbcUrl(url);
        source.setMaxLifetime(600000L);
        source.setConnectionTimeout(10000L);
        source.setLeakDetectionThreshold(300000L);
        source.setIdleTimeout(300000L);
    }

    @Override
    public void close() {
        source.close();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return source.getConnection();
    }
}
