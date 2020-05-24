package net.zey.bettersql.condition;

import net.zey.bettersql.database.SQLObject;

public class EqualCondition extends RepCondition{

    public EqualCondition(SQLObject obj, String name) {
        super(" WHERE " + name + " = ?", obj);
    }
}
