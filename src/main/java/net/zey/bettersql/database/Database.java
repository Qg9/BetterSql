package net.zey.bettersql.database;

import net.zey.bettersql.arguments.TableArguments;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class Database {

    private final String name;

    public Database(String name) {
        this.name = name;
    }

    public abstract String getURL();

    public abstract void connect();

    public abstract Connection getConnection() throws SQLException;

    public Table getTable(String name, List<TableArguments> args){
        return new Table(name, args, this);
    }

    public boolean isInLocal(){
        return this instanceof SqliteDatabase;
    }

    public String getName() {
        return name;
    }
}
