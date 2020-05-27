package net.zey.bettersql.condition;

import java.sql.Date;

public class DateCondition extends Condition{

    public DateCondition(String dateName, boolean isOudated) {
        super("CURDATE() " + getSymbol(isOudated) + " " + dateName);
    }

    private static String getSymbol(boolean isOudated){
        if(isOudated){
            return "<";
        }else{
            return ">";
        }
    }
}
