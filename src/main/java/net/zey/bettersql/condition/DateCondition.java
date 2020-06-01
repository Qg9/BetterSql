package net.zey.bettersql.condition;

import java.sql.Date;

public class DateCondition extends Condition{

    public DateCondition(String dateName, boolean isOutdated) {
        super("WHERE CURDATE() " + getSymbol(isOutdated) + " " + dateName);
    }

    private static String getSymbol(boolean isOutdated){
        if(isOutdated){
            return "<";
        }else{
            return ">";
        }
    }
}
