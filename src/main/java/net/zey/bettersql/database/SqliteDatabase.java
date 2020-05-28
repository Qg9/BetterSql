package net.zey.bettersql.database;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class SqliteDatabase extends Database{

    private String where;

    public SqliteDatabase(String where, String name){
        super(name);
        this.where = where;
    }

    @Override
    public String getURL(){
        return "jdbc:sqlite:" + where + "/" + getName() + ".db";
    }


    @Override
    public void connect() {
        if(!new File(where, getName() + ".db").exists()){
            try {
                new File(where, getName() + ".db").createNewFile();
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

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getURL());
    }

}
