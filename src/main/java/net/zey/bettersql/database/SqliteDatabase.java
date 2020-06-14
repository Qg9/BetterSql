package net.zey.bettersql.database;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class SqliteDatabase extends Database{

    public SqliteDatabase(String where, String name){
        try {

            File f = new File(where, name + ".db");
            if(!f.exists())f.createNewFile();

            Class.forName("org.sqlite.JDBC");
            setConnection(DriverManager.getConnection("jdbc:sqlite:" + where + "/" + name + ".db"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            getConnection().close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public boolean isConnected() {
        return getConnection() != null;
    }

    @Override
    public boolean isLocal() {
        return true;
    }
}
