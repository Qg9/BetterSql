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

    public List<Object> get() {

        if(all == null){
            try {
                throw new BetterSqlException("This request don't have any result.");
            } catch (BetterSqlException e) {
                e.printStackTrace();
            }
            return null;
        }

        if(all.isEmpty()){
            try {
                throw new BetterSqlException("The result is null.");
            } catch (BetterSqlException e) {
                e.printStackTrace();
            }
            return null;
        }

        return all;
    }
}
