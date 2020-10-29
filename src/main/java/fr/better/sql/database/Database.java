package fr.better.sql.database;

import fr.better.sql.database.arguments.TableArguments;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.util.Arrays;

public class Database {

    protected final String name;
    protected String url;

    public Database(String where, String name) {

        this.name = name;

        try {
            if(!new File(where).exists()) {
                new File(where).mkdir();
            }

            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + where + "/" + name + ".db");
            if (conn != null) {
                this.url = conn.getMetaData().getURL();
                conn.close();
            }

        }catch(Exception e) {
            url = "jdbc:sqlite:" + where + "/" + name + ".db";
            e.printStackTrace();
        }
    }

    public Table getTableAndCreateIt(String name, TableArguments... args){
        Table ct = new ComplexTable(name, Arrays.asList(args), new AdvancedDatabase(url, name), true);
        return ct;
    }

    public Table getTable(String name, TableArguments... args){
        return new ComplexTable(name, Arrays.asList(args),  new AdvancedDatabase(url, name), false);
    }
}
