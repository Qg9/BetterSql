package fr.better.sql.database.arguments;

public enum TableArgumentsType {

    VARCHAR("VARCHAR", "va"),
    TEXT("TEXT", "te"),
    INT("INT", "in"),
    DATE("DATE", "da"),
    BOOLEAN( "BOOLEAN", "bo");


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
