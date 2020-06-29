package net.zey.bettersql.help;

import java.sql.Date;

public class SQLObject {

    /*
        This library was made by Zey,
        The objective was to create a new Save System, more easy and swift :d
        You have no right to take back, copy or steal the code of this class or the entire library.
        You have more information on how to use the library in readme.md
        Thanks, Zey.
     */

    private Integer number;
    private String string;
    private Date date;

    public SQLObject(int n){
        this.number = n;
    }
    public SQLObject(String s){
        this.string = s;
    }
    public SQLObject(Date d){
        this.date = d;
    }

    public String getString() {
        return string;
    }
    public void setString(String s) {
        this.string = s;
    }
    public boolean isString(){
        return string != null;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date d) {
        this.date = d;
    }
    public boolean isDate(){
        return date != null;
    }

    public int getInt() { return number; }
    public void setInt(int i) {number = i; }
    public boolean isInt(){
        return number != null;
    }
}
