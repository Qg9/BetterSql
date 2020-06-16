package net.zey.bettersql.request;

import net.zey.bettersql.help.SQLObject;
import net.zey.bettersql.database.Table;
import net.zey.bettersql.help.SqlResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AddRequest extends Request{

    private List<SQLObject> all;

    public AddRequest(List<SQLObject> all, Table table, StringBuilder sql) {
        super(table, sql);
        this.all = all;
    }

    @Override
    public SqlResult sendSql(){
        String s = getSql().toString();
        if(getTable().getDatabase().isLocal()){
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
        return new SqlResult();
    }
}
