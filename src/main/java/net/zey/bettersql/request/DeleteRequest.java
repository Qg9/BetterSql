package net.zey.bettersql.request;

import net.zey.bettersql.condition.*;
import net.zey.bettersql.database.Table;
import net.zey.bettersql.help.SqlResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteRequest extends Request{

    public DeleteRequest(Table table, StringBuilder sql) {
        super(table, sql);
    }

    @Override
    public SqlResult sendSql() {
        String s = getSql().toString();
        if (getCondition() != null) {
            getSql().append(getCondition().getAdding());
        }
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = getTable().getDatabase().getConnection()){
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
        return new SqlResult();
    }

    /*public void sendSql() {
        String s = getSql().toString();
        if (getCondition() != null) {
            getSql().append(getCondition().getAdding());
        }
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = getTable().getDatabase().getConnection()){
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
    }*/
}
