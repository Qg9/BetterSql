package net.zey.bettersql.condition;

public class DateCondition extends Condition{

    /*

        This library was made by Zey,
        The objective was to create a new Save System, more easy and swift :d
        You have no right to take back, copy or steal the code of this class or the entire library.
        You have more information on how to use the library in readme.md
        Thanks, Zey.

     */

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
