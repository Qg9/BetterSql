package net.zey.bettersql.request;

import net.zey.bettersql.H;
import net.zey.bettersql.arguments.TableArguments;
import net.zey.bettersql.arguments.TableArgumentsType;
import net.zey.bettersql.condition.ClassicCondition;
import net.zey.bettersql.condition.Condition;
import net.zey.bettersql.condition.DateCondition;
import net.zey.bettersql.help.Symbol;
import net.zey.bettersql.help.SQLObject;
import net.zey.bettersql.database.Table;
import net.zey.bettersql.help.SqlResult;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Request {

    /*

        This library was made by Zey,
        The objective was to create a new Save System, more easy and swift :d
        You have no right to take back, copy or steal the code of this class or the entire library.
        You have more information on how to use the library in readme.md
        Thanks, Zey.

     */


    protected final Table table;

    protected StringBuilder sql;
    protected Condition condition;

    public Request(Table table, StringBuilder sql){
        this.table = table;
        this.sql = sql;
    }

    public abstract SqlResult sendSql();

    public Request where(String name, SQLObject sql){
        condition = new ClassicCondition(name, sql, Symbol.EQU);
        return this;
    }

    public Request where(String name, SQLObject sql, Symbol symbol){
        condition = new ClassicCondition(name,sql, symbol);
        return this;
    }

    public Request where(String column, boolean isOutdated){
        condition = new DateCondition(column, isOutdated);
        return this;
    }

    protected void set(int i, SQLObject o, PreparedStatement ps){
        try{
            if(o.isInt()){
                ps.setInt(i, o.getInt());
            }else if(o.isDate()){
                ps.setDate(i, o.getDate());
            }else if(o.isString()){
                ps.setString(i, o.getString());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    protected SQLObject get(TableArguments arguments, ResultSet resultSet) throws SQLException {
        TableArgumentsType type = arguments.getType();

        if(type == TableArgumentsType.VARCHAR || type == TableArgumentsType.TEXT){
            return H.ob(resultSet.getString(arguments.getName()));
        }else if(type == TableArgumentsType.INT){
            return H.ob(resultSet.getInt(arguments.getName()));
        }else if(type == TableArgumentsType.DATE) {
            return H.ob(resultSet.getDate(arguments.getName()));
        }
        return null;
    }
}
