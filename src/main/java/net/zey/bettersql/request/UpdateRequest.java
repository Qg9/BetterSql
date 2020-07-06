package net.zey.bettersql.request;

import net.zey.bettersql.condition.*;
import net.zey.bettersql.database.Table;
import net.zey.bettersql.help.SqlResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class UpdateRequest extends Request{

    /*

        This library was made by Zey,
        The objective was to create a new Save System, more easy and swift :d
        You have no right to take back, copy or steal the code of this class or the entire library.
        You have more information on how to use the library in readme.md
        Thanks, Zey.

     */

    private final Collection<Object> all;

    public UpdateRequest(Table table, StringBuilder sql, Collection<Object> all) {
        super(table, sql);
        this.all = all;
    }

    @Override
    public SqlResult sendSql(){
        if(condition != null){
            sql.append(condition.getAdding());
        }
        try {
            if(table.getDatabase().isLocal()){
                try {
                    Class.forName("org.sqlite.JDBC");
                } catch (ClassNotFoundException exception) {
                    exception.printStackTrace();
                }
            }
            Connection connection = table.getDatabase().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

            int i = 1;
            for(Object object : all){
                set(i, object, preparedStatement);
                i++;
            }

            if (condition != null) {
                if(condition instanceof RepCondition){
                    RepCondition condition = (RepCondition) this.condition;
                    set(i, condition.getObject(), preparedStatement);
                }
            }
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException exception){
            exception.printStackTrace();
        }
        return new SqlResult();
    }
}
