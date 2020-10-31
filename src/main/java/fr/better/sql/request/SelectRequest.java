package fr.better.sql.request;

import fr.better.sql.help.ResultBuilder;
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
            if(condition != null) {
                sql.append(condition.getAdding());
            }

            Class.forName("org.sqlite.JDBC");
            Connection connection = table.getDatabase().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

            if (condition != null && condition instanceof RepCondition) {
                RepCondition condition = (RepCondition) this.condition;
                preparedStatement.setObject(1, condition.getObject());
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            ResultBuilder rb = new ResultBuilder();

            if(condition != null){

                List<Object> all = new ArrayList<>();

                while (resultSet.next()) {

                    for (String name  : table.getNames()) {
                        Object object = resultSet.getObject(name);
                        all.add(object);
                    }
                    rb.setContent(all);
                }

            }else{

                List<List<Object>> content = new ArrayList<>();

                while (resultSet.next()) {

                    List<Object> all = new ArrayList<>();
                    for (String name  : table.getNames()) {
                        Object object = resultSet.getObject(name);
                        all.add(object);
                    }
                    content.add(all);
                }

                rb.setComplexContents(content);

            }
            resultSet.close();
            preparedStatement.close();
            return rb;
        }catch(SQLException | ClassNotFoundException exception){
            exception.printStackTrace();
        }
        return new ResultBuilder();
    }
}
