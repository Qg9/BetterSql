package net.zey.bettersql.help;

import java.util.List;

public class SqlResult {

    private List<SQLObject> all;

    public SqlResult(){}
    public SqlResult(List<SQLObject> result){
        all = result;
    }

    public List<SQLObject> get() { return all; }
}
