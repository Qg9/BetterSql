package fr.better.sql.exception;

public class OverComplexObjectException extends BetterSqlException{

    public OverComplexObjectException() {
        super("Your object is over complex ! Try with simple like String, int or boolean.");
    }
}
