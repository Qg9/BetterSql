package fr.better.sql.exception;

public class ConditionNotSetException extends BetterSqlException {

    public ConditionNotSetException() {
        super("You don't have set any condition in your request !");
    }
}
