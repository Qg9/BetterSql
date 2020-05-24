package net.zey.bettersql.database;

import java.sql.Date;

public class SQLObject {

    private Integer i;
    private String s;
    private Date d;

    public SQLObject(Integer i){
        this.i = i;
    }

    public SQLObject(String s){
        this.s = s;
    }

    public SQLObject(Date d){
        this.d = d;
    }

    public int getInt() {
        return i;
    }

    public void setInt(int i) {
        this.i = i;
    }

    public boolean isInt(){
        return i != null;
    }

    public String getString() {
        return s;
    }

    public void setString(String s) {
        this.s = s;
    }

    public boolean isString(){
        return s != null;
    }

    public Date getDate() {
        return d;
    }

    public void setDate(Date d) {
        this.d = d;
    }

    public boolean isDate(){
        return d != null;
    }
}
