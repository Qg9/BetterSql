package net.zey.bettersql.arguments;

public enum TableDefaultArgumentsType {

    /*

        This library was made by Zey,
        The objective was to create a new Save System, more easy and swift :d
        You have no right to take back, copy or steal the code of this class or the entire library.
        You have more information on how to use the library in readme.md
        Thanks, Zey.

     */

    //Null Default Value is set when you don't add a line,
    NULL("NULL", "nu"),
    //No default Value is used when the value is mandatory,
    NO("NO USED", "no"),
    //Current Timestamp is for date, the default value was the
    CURRENT_TIMESTAMP("CURRENT_TIMESTAMP", "cu"),
    //Default Value is a custom value, you can define here with ArgumentsType#setData(String value); (it return the ArgumentsType)
    DEFAULT_VALUE("NO USED", "de");

    private final String sql;
    private final String letter;

    TableDefaultArgumentsType(String sql, String letter) {
        this.sql = sql;
        this.letter = letter;
    }

    public String getSql() {
        return sql;
    }

    public String getLetter() {
        return letter;
    }

    public static TableDefaultArgumentsType getByLetter(String letter){
        for(TableDefaultArgumentsType args : TableDefaultArgumentsType.values()){
            if(args.letter.equalsIgnoreCase(letter))return args;
        }
        return TableDefaultArgumentsType.NO;
    }
}
