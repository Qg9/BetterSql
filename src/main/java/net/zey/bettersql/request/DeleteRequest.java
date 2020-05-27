package net.zey.bettersql.request;

import net.zey.bettersql.condition.*;
import net.zey.bettersql.database.SQLObject;
import net.zey.bettersql.database.Table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteRequest extends Request{

    public DeleteRequest(Table table, StringBuilder sql) {
        super(table, sql);
    }


    public DeleteRequest where(String name, SQLObject sql, Sym symbol){
        setCondition(new ClassicCondition(name, sql, symbol));
        return this;
    }

    public DeleteRequest whereDate(String column, boolean isOutaded){
        setCondition(new DateCondition(column, isOutaded));
        return this;
    }

    public void sendSql() {
        String s = getSql().toString();
        if (getCondition() != null) {
            getSql().append(getCondition().getAdding());
        }
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(getTable().getData().getURL())){
            PreparedStatement p = conn.prepareStatement(s);

            if (getCondition() != null) {
                if (getCondition() instanceof RepCondition) {
                    RepCondition cond = (RepCondition) getCondition();
                    set(1, cond.getObj(), p);
                }
            }

            p.executeUpdate();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
