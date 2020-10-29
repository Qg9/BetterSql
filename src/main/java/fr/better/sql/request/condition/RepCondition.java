package fr.better.sql.request.condition;

public class RepCondition extends Condition{

    private final Object object;

    public RepCondition(String adding, Object object) {
        super(adding);
        this.object = object;
    }

    public Object getObject() {
        return object;
    }
}
