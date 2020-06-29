package net.zey.bettersql.database;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariDatabase extends Database {

    /*

        This library was made by Zey,
        The objective was to create a new Save System, more easy and swift :d
        You have no right to take back, copy or steal the code of this class or the entire library.
        You have more information on how to use the library in readme.md
        Thanks, Zey.

     */

    private HikariDataSource source;

    public HikariDatabase(String name, String user, String password, String host, int port, int maxPoolSize){
        super(name);
        source = new HikariDataSource();
        source.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + name);
        source.setUsername(user);
        source.setPassword(password);
        source.setMaximumPoolSize(maxPoolSize);
        source.setIdleTimeout(300000L);
        source.setConnectionTimeout(10000L);
        source.setMaxLifetime(600000L);
        source.setLeakDetectionThreshold(300000L);
    }

    @Override
    public void close() {
        source.close();
    }

    @Override
    public boolean isLocal() {
        return false;
    }

    @Override
    public Connection getConnection() {
        try {
            return source.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isConnected() {
        try {
            return source.getConnection() != null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
