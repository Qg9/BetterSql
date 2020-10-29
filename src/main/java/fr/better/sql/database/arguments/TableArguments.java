package fr.better.sql.database.arguments;

public class TableArguments {

    private final TableArgumentsType type;
    private final String name;
    private final int value;

    private boolean unique;

    public TableArguments(TableArgumentsType type, String name, int value) {
        this.type = type;
        this.name = name;
        this.value = value;
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

    public boolean isUnique() {
        return unique;
    }

    public TableArguments setUnique(boolean unique) {
        this.unique = unique;
        return this;
    }
}
