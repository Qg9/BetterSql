package net.zey.bettersql.condition;

import net.zey.bettersql.help.Symbol;

public class ClassicCondition extends RepCondition{

    /*

        This library was made by Zey,
        The objective was to create a new Save System, more easy and swift :d
        You have no right to take back, copy or steal the code of this class or the entire library.
        You have more information on how to use the library in readme.md
        Thanks, Zey.

     */

    public ClassicCondition(String name, Object obj, Symbol symbol) {
        super(" WHERE " + name + " " + symbol.getSymbol() + " ?", obj);
    }
}
