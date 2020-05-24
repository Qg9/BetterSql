package net.zey.bettersql.database;

import net.zey.bettersql.arguments.TableArguments;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Database {


    private String name;
    private String where;

    public String getURL(){
        return "jdbc:sqlite:" + where + "/" + name + ".db";
    }

    public Database(String where, String name){
        this.where = where;
        this.name = name;
    }

    public void connect() {
        if(!new File(where, name + ".db").exists()){
            try {
                new File(where, name + ".db").createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Connection conn = null;
            try {
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection(getURL());
                if (conn != null) {
                    DatabaseMetaData meta = conn.getMetaData();
                    System.out.println("The driver name is " + meta.getDriverName());
                    System.out.println("A new database has been created.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }else {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(getURL());

                System.out.println("Connection to SQLite has been established.");

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException ex) {}
            }
        }
    }

    public Table getTable(String name, List<TableArguments> args){
        return new Table(name, args, this);
    }
}
