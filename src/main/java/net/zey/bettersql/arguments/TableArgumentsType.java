package net.zey.bettersql.arguments;

public enum TableArgumentsType {

    //Varchar is a small string like a name or a password.
    VARCHAR("VARCHAR", "va"),
    //Text is a more long string like a biographie or an description
    TEXT("TEXT", "te"),
    //INT is an int :D
    INT("INT", "in"),
    /* This new type was for update
    LONG("LONG", "lo"),
    DECIMAL("DECIMAL", "de"),
    FLOAT("FLOAT", "fl"),
    SHORT("SHORT", "sh"),
   */
    //Date is a date like Monday 5th april you can test if the date is outdated
    DATE("DATE", "da"),
    ;

    private final String sql;
    private final String letter;

    TableArgumentsType(String sql, String letter){
        this.sql = sql;
        this.letter = letter;
    }

    public String getSql() {
        return sql;
    }

    public static TableArgumentsType getByLetter(String letter){
        for(TableArgumentsType args : TableArgumentsType.values()){
            if(args.letter.equalsIgnoreCase(letter))return args;
        }
        return TableArgumentsType.VARCHAR;
    }
}
