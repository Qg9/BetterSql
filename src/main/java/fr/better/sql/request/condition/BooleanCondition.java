package fr.better.sql.request.condition;

public class BooleanCondition extends Condition{

    public BooleanCondition(String column, Boolean is) {
        super(column + " IS " + is.toString().toUpperCase());
    }

}
