package net.zey.bettersql.condition;

import net.zey.bettersql.help.SQLObject;
import net.zey.bettersql.help.Symbol;

public class ClassicCondition extends RepCondition{

    public ClassicCondition(String name, SQLObject obj, Symbol symbol) {
        super(" WHERE " + name + " " + symbol.getSymbol() + " ?", obj);
    }
}
