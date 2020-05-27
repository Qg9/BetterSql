package net.zey.bettersql.condition;

public enum Sym {

    SUP(">"), INF("<"), EQU("=");

    private final String symbol;

    Sym(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
