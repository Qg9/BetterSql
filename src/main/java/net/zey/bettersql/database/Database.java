package net.zey.bettersql.database;

import net.zey.bettersql.arguments.TableArguments;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public abstract class Database {

    /*

        This library was made by Zey,
        The objective was to create a new Save System, more easy and swift :d
        You have no right to take back, copy or steal the code of this class or the entire library.
        You have more information on how to use the library in readme.md
        Thanks, Zey.

     */

    private final String name;

    public Database(String name) {
        this.name = name;
    }

    public abstract void close();

    public abstract boolean isLocal();
    public abstract Connection getConnection() throws SQLException;

    public Table getTable(String name, TableArguments... args){
        return new Table(name, Arrays.asList(args), this);
    }

    public String getName() {
        return name;
    }

}
