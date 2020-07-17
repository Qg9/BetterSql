package net.zey.bettersql.result;

import net.zey.bettersql.help.BetterSqlException;

import java.util.List;

public class Result {

    /*
       This library was made by Zey,
       The objective was to create a new Save System, more easy and swift :d
       You have no right to take back, copy or steal the code of this class or the entire library.
       You have more information on how to use the library in readme.md
       Thanks, Zey.
    */

    private List<Object> all;
    private List<List<Object>> alls;

    public Result(){}

    public List<Object> get() throws BetterSqlException {

        if(all == null){
            throw new BetterSqlException("This request don't have any result.");
        }

        if(all.isEmpty()){
            throw new BetterSqlException("The result is null.");
        }

        return all;
    }

    public List<List<Object>> gets() throws BetterSqlException {

        if(alls == null){
            throw new BetterSqlException("This request don't have any result.");
        }

        if(alls.isEmpty()){
            throw new BetterSqlException("The result is null.");
        }

        return alls;
    }

    public Result setAll(List<Object> all) {
        this.all = all;
        return this;
    }

    public Result setAlls(List<List<Object>> alls) {
        this.alls = alls;
        return this;
    }
}
