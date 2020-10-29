package fr.better.sql.request;



import fr.better.sql.request.condition.RepCondition;
import fr.better.sql.database.ComplexTable;
import fr.better.sql.help.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class UpdateRequest extends Request{

    private final Collection<Object> all;

    public UpdateRequest(ComplexTable table, StringBuilder sql, Collection<Object> all) {
        super(table, sql);
        this.all = all;
    }

    @Override
    public Result execute() throws NullPointerException{
        if(condition != null){
            sql.append(condition.getAdding());
        }
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = table.getDatabase().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

            int i = 1;
            for(Object object : all){
                preparedStatement.setObject(i, object);
                i++;
            }

            if (condition != null) {
                if(condition instanceof RepCondition){
                    RepCondition condition = (RepCondition) this.condition;
                    preparedStatement.setObject(i, condition.getObject());
                }
            }
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }
        catch (SQLException | ClassNotFoundException exception){
            exception.printStackTrace();
        }
        return new Result();
    }
}
