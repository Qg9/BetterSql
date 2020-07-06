package net.zey.bettersql.database;

import net.zey.bettersql.arguments.TableArguments;
import net.zey.bettersql.arguments.TableDefaultArgumentsType;
import net.zey.bettersql.request.AddRequest;
import net.zey.bettersql.request.DeleteRequest;
import net.zey.bettersql.request.SelectRequest;
import net.zey.bettersql.request.UpdateRequest;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Table {

    /*

        This library was made by Zey,
        The objective was to create a new Save System, more easy and swift :d
        You have no right to take back, copy or steal the code of this class or the entire library.
        You have more information on how to use the library in readme.md
        Thanks, Zey.

     */

    private final String name;
    private final List<TableArguments> args;
    private final Database data;

    public Table(String name, List<TableArguments> args, Database data){
        this.data = data;
        this.args = args;
        this.name = name;
    }

    public void createTable(){

        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS '" + this.name + "' (");

        for(TableArguments arguments : args){
            sql.append("'" + arguments.getName() + "' " + arguments.getType().getSql() + "(" + arguments.getValue() + ") ");
            if(arguments.getDefaultType() != TableDefaultArgumentsType.NULL){
                sql.append("NOT ");
            }
            sql.append("NULL ");
            if(arguments.getDefaultType() != TableDefaultArgumentsType.NO){
                sql.append("DEFAULT ");
                if(arguments.getDefaultType() != TableDefaultArgumentsType.DEFAULT_VALUE){
                    sql.append(arguments.getDefaultType().getSql());
                }else{
                    sql.append("'" + arguments.getData() + "'");
                }
            }
            if(!arguments.equals(args.get(args.size() - 1))){
                sql.append(" , ");
            }
        }
        sql.append(")");

        if(getDatabase().isLocal()){
            try{
                Class.forName("org.sqlite.JDBC");
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        try{
            Connection connection = data.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    public DeleteRequest delete(){
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM " + name);
        return new DeleteRequest(this, sql);
    }

    public UpdateRequest update(HashMap<String, Object> all){
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE " + name + " SET ");

        int column = 0;
        for(String up : all.keySet()){
            if(column == 0) {
                sql.append( up + " = ?");
            }else{
                sql.append(", " + up + " = ?");
            }
            column++;
        }
        return new UpdateRequest(this, sql, all.values());
    }

    public UpdateRequest updateAll(Object... all){
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE " + name + " SET ");

        List<String> names = new ArrayList<>();
        for(TableArguments arg : args){
            names.add(arg.getName());
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

    public SelectRequest select(){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM " + name);
        return new SelectRequest(this, sql);
    }

    public String getName() {
        return name;
    }

    public Database getDatabase() {
        return data;
    }

    public List<TableArguments> getArgs(){
        return args;
    }
}
