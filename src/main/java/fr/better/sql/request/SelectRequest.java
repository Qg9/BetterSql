package fr.better.sql.request;

import fr.better.sql.request.condition.RepCondition;
import fr.better.sql.database.ComplexTable;
import fr.better.sql.exception.ConditionNotSetException;
import fr.better.sql.help.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectRequest extends Request{

    public SelectRequest(ComplexTable table, StringBuilder sql) {
        super(table, sql);
    }

    @Override
    public Result execute(){
        try {

            if(condition == null)throw new ConditionNotSetException();

            sql.append(condition.getAdding());

            Class.forName("org.sqlite.JDBC");
            Connection connection = table.getDatabase().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

            if (condition instanceof RepCondition) {
                RepCondition condition = (RepCondition) this.condition;
                preparedStatement.setObject(1, condition.getObject());
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Object> all = new ArrayList<>();

            while (resultSet.next()) {
                for (String name  : table.getNames()) {
                        Object object = resultSet.getObject(name);
                        all.add(object);
                }
            }

            resultSet.close();
            preparedStatement.close();

            return new Result().setAll(all);

        }catch(SQLException | ConditionNotSetException | ClassNotFoundException exception){
            exception.printStackTrace();
        }
        return new Result();
    }
}
