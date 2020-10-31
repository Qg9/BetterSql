package fr.better.sql.request;

import fr.better.sql.help.ResultBuilder;
import fr.better.sql.request.condition.RepCondition;
import fr.better.sql.database.ComplexTable;
import fr.better.sql.help.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteRequest extends Request{

    public DeleteRequest(ComplexTable table, StringBuilder sql) {
        super(table, sql);
    }

    @Override
    public Result execute(){
        if (condition != null) {
            sql.append(condition.getAdding());
        }

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = table.getDatabase().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

            if (condition != null) {
                if (condition instanceof RepCondition) {
                    RepCondition cond = (RepCondition) condition;
                    preparedStatement.setObject(1, cond.getObject());
                }
            }

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return new ResultBuilder();
    }
}
