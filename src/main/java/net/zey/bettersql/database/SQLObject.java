package net.zey.bettersql.database;

import java.sql.Date;

public class SQLObject {

    private Integer n;
    private String s;
    private Date d;

    public SQLObject(int n){
        this.n = n;
    }

    public SQLObject(String s){
        this.s = s;
    }

    public SQLObject(Date d){
        this.d = d;
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

    public boolean isInt(){
        return n != null;
    }

    public int getInt() { return n; }
}
