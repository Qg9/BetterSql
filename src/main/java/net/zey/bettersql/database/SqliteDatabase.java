package net.zey.bettersql.database;

import java.io.File;
import java.sql.*;

public class SqliteDatabase extends Database{

    /*

        This library was made by Zey,
        The objective was to create a new Save System, more easy and swift :d
        You have no right to take back, copy or steal the code of this class or the entire library.
        You have more information on how to use the library in readme.md
        Thanks, Zey.

     */

    public SqliteDatabase(String where, String name){
        super(name);
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
