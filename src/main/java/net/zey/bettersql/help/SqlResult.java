package net.zey.bettersql.help;

import java.util.List;

public class SqlResult {

    /*
        This library was made by Zey,
        The objective was to create a new Save System, more easy and swift :d
        You have no right to take back, copy or steal the code of this class or the entire library.
        You have more information on how to use the library in readme.md
        Thanks, Zey.
     */

    private List<SQLObject> all;

    public SqlResult(){}
    public SqlResult(List<SQLObject> result){
        all = result;
    }

    public List<SQLObject> get() { return all; }
}
