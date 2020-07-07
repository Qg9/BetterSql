package net.zey.bettersql.request;

import net.zey.bettersql.arguments.TableArguments;
import net.zey.bettersql.condition.*;
import net.zey.bettersql.database.Table;
import net.zey.bettersql.help.BetterSqlException;
import net.zey.bettersql.help.SqlResult;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectRequest extends Request{

    /*

        This library was made by Zey,
        The objective was to create a new Save System, more easy and swift :d
        You have no right to take back, copy or steal the code of this class or the entire library.
        You have more information on how to use the library in readme.md
        Thanks, Zey.
    */

    public SelectRequest(Table table, StringBuilder sql) {
        super(table, sql);
    }

    @Override
    public SqlResult sendSql(){
        try {
            if (condition != null) {
                sql.append(condition.getAdding());
            }
            if(table.getDatabase().isLocal()){
                try {
                    Class.forName("org.sqlite.JDBC");
                } catch (ClassNotFoundException exception) {
                    exception.printStackTrace();
                }
            }
            Connection connection = table.getDatabase().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

            if (condition != null) {
                if (condition instanceof RepCondition) {
                    RepCondition condition = (RepCondition) this.condition;
                    set(1, condition.getObject(), preparedStatement);
                }
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Object> all = new ArrayList<>();
            while (resultSet.next()) {
                for (TableArguments tableArguments : table.getArgs()) {
                    Object object = get(tableArguments, resultSet);
                    all.add(object);
                }
            }

            resultSet.close();
            preparedStatement.close();

            return new SqlResult(all);
        }catch(SQLException exception){
            exception.printStackTrace();
        }
        return new SqlResult(new ArrayList<>());
    }

}
