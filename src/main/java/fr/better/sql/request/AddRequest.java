package fr.better.sql.request;

import fr.better.sql.database.ComplexTable;
import fr.better.sql.help.Result;
import fr.better.sql.help.ResultBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AddRequest extends Request{

    private final List<Object> all;

    public AddRequest(List<Object> all, ComplexTable table, StringBuilder sql) {
        super(table, sql);
        this.all = all;
    }

    @Override
    public Result execute(){
        String s = sql.toString();

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = table.getDatabase().getConnection()){
            PreparedStatement p = conn.prepareStatement(s);

            int i = 1;
            for(Object o : all){
                p.setObject(i, o);
                i++;
            }

            p.executeUpdate();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ResultBuilder();
    }
}
