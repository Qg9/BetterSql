package net.zey.bettersql.condition;

import net.zey.bettersql.database.SQLObject;

public class ClassicCondition extends RepCondition{

    public ClassicCondition(String name, SQLObject obj, Sym symbol) {
        super("WHERE " + name + " " + symbol.getSymbol() + " ?", obj);
    }
}
