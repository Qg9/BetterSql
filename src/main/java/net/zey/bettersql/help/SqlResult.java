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

    private List<Object> all;

    public SqlResult(){}
    public SqlResult(List<Object> result){
        all = result;
    }

    public List<Object> get() throws BetterSqlException {

        if(all == null){
            throw new BetterSqlException("This request don't have any result.");
        }

        if(all.isEmpty()){
            throw new BetterSqlException("The result is null.");
        }

        return all;
    }
}
