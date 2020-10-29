package fr.better.sql.request;

import fr.better.sql.database.ComplexTable;
import fr.better.sql.help.Result;
import fr.better.sql.request.condition.ClassicCondition;
import fr.better.sql.request.condition.Condition;
import fr.better.sql.request.condition.DateCondition;
import fr.better.sql.exception.BetterSqlException;
import fr.better.sql.help.Symbol;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Request {

    protected final ComplexTable table;

    protected StringBuilder sql;
    protected Condition condition;

    public Request(ComplexTable table, StringBuilder sql){
        this.table = table;
        this.sql = sql;
    }

    public abstract Result execute() throws NullPointerException;

    public Request where(String name, Object sql){
        condition = new ClassicCondition(name, sql, Symbol.EQUAL);
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
}
