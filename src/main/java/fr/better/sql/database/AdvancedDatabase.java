package fr.better.sql.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdvancedDatabase{

    private final String name;
    private final String url;

    public AdvancedDatabase(String url, String name) {
        this.name = name;
        this.url = url;
    }

    public String getName(){
        return name;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }
}
