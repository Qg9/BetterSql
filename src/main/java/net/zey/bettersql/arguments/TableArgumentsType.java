package net.zey.bettersql.arguments;

public enum TableArgumentsType {

    VARCHAR("VARCHAR", "va"),
    TEXT("TEXT", "te"),
    INT("INT", "in"),
    /*LONG("LONG", "lo"),
    DECIMAL("DECIMAL", "de"),
    FLOAT("FLOAT", "fl"),
    SHORT("SHORT", "sh"),
   */ DATE("DATE", "da"),
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
