package fr.better.sql.database;

import fr.better.sql.database.arguments.TableArguments;
import fr.better.sql.request.AddRequest;
import fr.better.sql.request.DeleteRequest;
import fr.better.sql.request.SelectRequest;
import fr.better.sql.request.UpdateRequest;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComplexTable implements Table{

    private final String name;
    private final AdvancedDatabase data;
    private final List<String> names;

    public ComplexTable(String name, List<TableArguments> args, AdvancedDatabase data, boolean wantToCreateIt){
        this.data = data;
        this.name = name;
        this.names = new ArrayList<>();
        for(TableArguments arg : args) names.add(arg.getName());

        if(wantToCreateIt)createTable(args);
    }

    public void createTable(List<TableArguments> args){

        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS '" + this.name + "' (");

        for(TableArguments arguments : args){

            sql.append("'" + arguments.getName() + "' " + arguments.getType().getSql());

            if(arguments.getValue() != 0){
                sql.append("(" + arguments.getValue() + ") ");
            }

            if(!arguments.equals(args.get(args.size() - 1))){
                sql.append(" , ");
            }
        }
        sql.append(")");

        try{
            Connection connection = data.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AddRequest add(Object... all){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO " + name +  " VALUES(?" );
        for(Object o : all){
            if(o == Arrays.asList(all).get(0))continue;
            sql.append(",?");
        }
        sql.append(")");
        return new AddRequest(Arrays.asList(all), this, sql);
    }

    @Override
    public DeleteRequest delete(){
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM " + name);
        return new DeleteRequest(this, sql);
    }

    @Override
    public UpdateRequest update(Object... all){
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE " + name + " SET ");

        List<String> names = new ArrayList<>();
        for(String name: names){
            names.add(name);
        }

        int column = 0;
        for(String up : names) {
            if (column == 0) {
                sql.append(up + " = ?");
            } else {
                sql.append(", " + up + " = ?");
            }
            column++;
        }

        return new UpdateRequest(this, sql, Arrays.asList(all));
    }

    @Override
    public SelectRequest select(){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM " + name);
        return new SelectRequest(this, sql);
    }

    public String getName() {
        return name;
    }

    public AdvancedDatabase getDatabase() {
        return data;
    }

    public List<String> getNames() {
        return names;
    }
}
