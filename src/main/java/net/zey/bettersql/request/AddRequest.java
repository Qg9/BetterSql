package net.zey.bettersql.request;

import net.zey.bettersql.database.Table;
import net.zey.bettersql.result.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AddRequest extends Request{

    /*

        This library was made by Zey,
        The objective was to create a new Save System, more easy and swift :d
        You have no right to take back, copy or steal the code of this class or the entire library.
        You have more information on how to use the library in readme.md
        Thanks, Zey.

     */

    private final List<Object> all;

    public AddRequest(List<Object> all, Table table, StringBuilder sql) {
        super(table, sql);
        this.all = all;
    }

    @Override
    public Result sendSql(){
        String s = sql.toString();
        if(table.getDatabase().isLocal()){
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        try (Connection conn = table.getDatabase().getConnection()){
            PreparedStatement p = conn.prepareStatement(s);

            int i = 1;
            for(Object o : all){
                set(i, o, p);
                i++;
            }

            p.executeUpdate();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Result();
    }
}
