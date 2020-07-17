package net.zey.bettersql.request;

import net.zey.bettersql.arguments.TableArguments;
import net.zey.bettersql.arguments.TableArgumentsType;
import net.zey.bettersql.condition.ClassicCondition;
import net.zey.bettersql.condition.Condition;
import net.zey.bettersql.condition.DateCondition;
import net.zey.bettersql.help.BetterSqlException;
import net.zey.bettersql.help.Symbol;
import net.zey.bettersql.database.Table;
import net.zey.bettersql.result.Result;

import java.sql.Date;
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

    public abstract Result sendSql();

    public Request where(String name, Object sql){
        condition = new ClassicCondition(name, sql, Symbol.EQU);
        return this;
    }

    public Request where(String name, Object sql, Symbol symbol){
        condition = new ClassicCondition(name, sql, symbol);
        return this;
    }

    public Request where(String column, boolean isOutdated){
        condition = new DateCondition(column, isOutdated);
        return this;
    }

    protected void set(int i, Object o, PreparedStatement ps){
        try{
            if(o instanceof Integer){
                ps.setInt(i, ((int)o));
            }else if(o instanceof Date){
                ps.setDate(i, ((Date)o));
            }else if(o instanceof String){
                ps.setString(i, ((String)o));
            }else if(o instanceof Double){
                ps.setDouble(i, ((Double)o));
            }else{
                throw new BetterSqlException("The object in the preparedStatement wasn't defined in your version !!");
            }
        }catch(SQLException | BetterSqlException e){
            e.printStackTrace();
        }
    }

    protected Object get(TableArguments arguments, ResultSet resultSet) throws SQLException {
        TableArgumentsType type = arguments.getType();

        if(type == TableArgumentsType.VARCHAR || type == TableArgumentsType.TEXT){
            return resultSet.getString(arguments.getName());
        }else if(type == TableArgumentsType.INT){
            return resultSet.getInt(arguments.getName());
        }else if(type == TableArgumentsType.DATE) {
            return resultSet.getDate(arguments.getName());
        }
        return null;
    }
}
