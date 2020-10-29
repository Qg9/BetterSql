package fr.better.sql.help;

public enum Symbol {

    /*
        This library was made by Zey,
        The objective was to create a new Save System, more easy and swift :d
        You have no right to take back, copy or steal the code of this class or the entire library.
        You have more information on how to use the library in readme.md
        Thanks, Zey.
     */

    SUPERIOR(">"), INFERIOR("<"), EQUAL("=");

    private final String symbol;

    Symbol(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
