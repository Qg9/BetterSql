package net.zey.bettersql.request;

import net.zey.bettersql.condition.*;
import net.zey.bettersql.database.Table;
import net.zey.bettersql.help.SqlResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteRequest extends Request{

    /*

        This library was made by Zey,
        The objective was to create a new Save System, more easy and swift :d
        You have no right to take back, copy or steal the code of this class or the entire library.
        You have more information on how to use the library in readme.md
        Thanks, Zey.

     */

    public DeleteRequest(Table table, StringBuilder sql) {
        super(table, sql);
    }

    @Override
    public SqlResult sendSql() {
        if (condition != null) {
            sql.append(condition.getAdding());
        }
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        try (Connection connection = table.getDatabase().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

            if (condition != null) {
                if (condition instanceof RepCondition) {
                    RepCondition cond = (RepCondition) condition;
                    set(1, cond.getObject(), preparedStatement);
                }
            }

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return new SqlResult();
    }
}
