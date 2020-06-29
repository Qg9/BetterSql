package net.zey.bettersql.arguments;

public class TableArguments {

    /*

        This library was made by Zey,
        The objective was to create a new Save System, more easy and swift :d
        You have no right to take back, copy or steal the code of this class or the entire library.
        You have more information on how to use the library in readme.md
        Thanks, Zey.

     */

    private final TableArgumentsType type;
    private final String name;
    private final int value;

    private final TableDefaultArgumentsType defaultType;
    private String data;

    private boolean unique;

    public TableArguments(TableArgumentsType type, String name, int value, TableDefaultArgumentsType defaultType) {
        this.type = type;
        this.name = name;
        this.value = value;
        this.defaultType = defaultType;
    }

    public TableArgumentsType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public TableDefaultArgumentsType getDefaultType() {
        return defaultType;
    }

    public String getData() {
        return data;
    }

    public TableArguments setData(String data) {
        this.data = data;
        return this;
    }

    public boolean isUnique() {
        return unique;
    }

    public TableArguments setUnique(boolean unique) {
        this.unique = unique;
        return this;
    }
}
