package net.zey.bettersql.request;

import net.zey.bettersql.database.SQLObject;
import net.zey.bettersql.database.Table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AddRequest extends Request{

    private List<SQLObject> all;

    public AddRequest(List<SQLObject> all, Table table, StringBuilder sql) {
        super(table, sql);
        this.all = all;
    }

    public void sendSql(){
        String s = getSql().toString();
        if(getTable().getDatabase().isInLocal()){
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        try (Connection conn = getTable().getDatabase().getConnection()){
            PreparedStatement p = conn.prepareStatement(s);

            int i = 1;
            for(SQLObject o : all){
                set(i, o, p);
                i++;
            }

            p.executeUpdate();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
