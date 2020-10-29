package fr.better.sql.request.condition;

import fr.better.sql.help.Symbol;

public class ClassicCondition extends RepCondition{

    public ClassicCondition(String name, Object obj, Symbol symbol) {
        super(" WHERE " + name + " " + symbol.getSymbol() + " ?", obj);
    }
}
