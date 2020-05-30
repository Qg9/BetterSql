package net.zey.bettersql.database;

import net.zey.bettersql.arguments.TableArguments;
import net.zey.bettersql.arguments.TableDefaultArgumentsType;
import net.zey.bettersql.request.AddRequest;
import net.zey.bettersql.request.DeleteRequest;
import net.zey.bettersql.request.SelectRequest;
import net.zey.bettersql.request.UpdateRequest;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

public class Table {


    private String name;
    private List<TableArguments> args;
    private Database data;

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

        if(getData().isInLocal()){
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

    public AddRequest add(List<SQLObject> all){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO " + name +  " VALUES(?" );
        for(Object o : all){
            if(o == all.get(0))continue;
            sql.append(",?");
        }
        sql.append(")");
        return new AddRequest(all, this, sql);
    }

    public DeleteRequest delete(){
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM " + name);
        return new DeleteRequest(this, sql);
    }

    public UpdateRequest update(HashMap<String, SQLObject> all){
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
        return new UpdateRequest(this, sql, all);
    }

    public SelectRequest select(){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM " + name);
        return new SelectRequest(this, sql);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TableArguments> getArgs() {
        return args;
    }

    public void setArgs(List<TableArguments> args) {
        this.args = args;
    }

    public Database getData() {
        return data;
    }

    public void setData(Database data) {
        this.data = data;
    }
}
