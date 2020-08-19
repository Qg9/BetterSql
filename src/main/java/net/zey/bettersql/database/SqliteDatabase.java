package net.zey.bettersql.database;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class SqliteDatabase extends Database{

    /*

        This library was made by Zey,
        The objective was to create a new Save System, more easy and swift :d
        You have no right to take back, copy or steal the code of this class or the entire library.
        You have more information on how to use the library in readme.md
        Thanks, Zey.

     */

    private String url;

    public SqliteDatabase(String where, String name) {
        super(name);

        try {
            if(!new File(where).exists()) {
                new File(where).mkdir();
            }
            Connection conn = null;
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + where + "/" + getName() + ".db");
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                this.url = meta.getURL();
            }
            if (conn != null) {
                conn.close();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close(){}

    @Override
    public boolean isLocal() {
        return true;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }

}
