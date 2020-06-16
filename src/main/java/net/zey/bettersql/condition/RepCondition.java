package net.zey.bettersql.condition;

import net.zey.bettersql.help.SQLObject;

public class RepCondition extends Condition{

    private SQLObject obj;

    public RepCondition(String adding, SQLObject obj) {
        super(adding);
        this.obj = obj;
    }

    public SQLObject getObj() {
        return obj;
    }

    public void setObj(SQLObject obj) {
        this.obj = obj;
    }
}
