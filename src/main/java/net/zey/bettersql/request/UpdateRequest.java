package net.zey.bettersql.request;

import net.zey.bettersql.condition.*;
import net.zey.bettersql.help.SQLObject;
import net.zey.bettersql.database.Table;
import net.zey.bettersql.help.SqlResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class UpdateRequest extends Request{


    private final Collection<SQLObject> all;

    public UpdateRequest(Table table, StringBuilder sql, Collection<SQLObject> all) {
        super(table, sql);
        this.all = all;
    }

    @Override
    public SqlResult sendSql(){
        if(getCondition() != null){
            getSql().append(getCondition().getAdding());
        }
        try {
            if(getTable().getDatabase().isLocal()){
                try {
                    Class.forName("org.sqlite.JDBC");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            Connection conn = getTable().getDatabase().getConnection();
            PreparedStatement p = conn.prepareStatement(getSql().toString());

            int i = 1;
            for(SQLObject o : all){
                set(i, o, p);
                i++;
            }

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
        return new SqlResult();
    }
}
