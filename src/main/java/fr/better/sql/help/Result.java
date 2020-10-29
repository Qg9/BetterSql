package fr.better.sql.help;

import fr.better.sql.exception.BetterSqlException;

import java.util.List;

public class Result {

    private List<Object> all;

    public Result(){}

    public List<Object> get() throws BetterSqlException {

        if(all == null){
            throw new NullPointerException("This request don't have any result.");
        }

        if(all.isEmpty()){
            throw new BetterSqlException("The result of your request os ");
        }

        return all;
    }

    public Result setAll(List<Object> all) {
        this.all = all;
        return this;
    }
}
