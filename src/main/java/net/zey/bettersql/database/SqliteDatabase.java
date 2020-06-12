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
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection(getURL());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception ex) {}
            }
        }
    }

    @Override
    public void close(){
        try {
            getConnection().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getURL());
    }

}
