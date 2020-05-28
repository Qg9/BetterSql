package net.zey.bettersql.request;

import net.zey.bettersql.condition.*;
import net.zey.bettersql.database.SQLObject;
import net.zey.bettersql.database.Table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class UpdateRequest extends Request{


    private final HashMap<String, SQLObject> all;

    public UpdateRequest(Table table, StringBuilder sql, HashMap<String, SQLObject> all) {
        super(table, sql);
        this.all = all;
    }

    public UpdateRequest where(String name, SQLObject sql, Sym symbol){
        setCondition(new ClassicCondition(name, sql, symbol));
        return this;
    }

    public UpdateRequest whereDate(String column, boolean isOutaded){
        setCondition(new DateCondition(column, isOutaded));
        return this;
    }

    public void sendSql(){
        if(getCondition() != null){
            getSql().append(getCondition().getAdding());
        }
        try {
            if(getTable().getData().isInLocal()){
                try {
                    Class.forName("org.sqlite.JDBC");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            Connection conn = getTable().getData().getConnection();
            PreparedStatement p = conn.prepareStatement(getSql().toString());

            int i = 1;
            for(SQLObject o : all.values()){
                set(i, o, p);
                i++;
            }
            System.out.println(getSql().toString());

            if (getCondition() != null) {
                if(getCondition() instanceof RepCondition){
                    RepCondition cond = (RepCondition) getCondition();
                    set(i, cond.getObj(), p);
                }
            }
            p.executeUpdate();
            p.close();
        }
        catch (SQLException se){
            se.printStackTrace();
        }
    }
}
