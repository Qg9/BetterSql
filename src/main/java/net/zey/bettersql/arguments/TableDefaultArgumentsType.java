package net.zey.bettersql.arguments;

public enum TableDefaultArgumentsType {

    CURRENT_TIMESTAMP("CURRENT_TIMESTAMP", "cu"),
    NULL("NULL", "nu"),
    NO("NO USED", "no"),
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
