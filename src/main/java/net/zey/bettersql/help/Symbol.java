package net.zey.bettersql.help;

public enum Symbol {

    SUP(">"), INF("<"), EQU("=");

    private final String symbol;

    Symbol(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
