package net.zey.bettersql.arguments;

public class TableArguments {


    private TableArgumentsType type;
    private String name;
    private int value;

    private TableDefaultArgumentsType defaultType;
    private String data;

    private boolean unique;

    public TableArguments(TableArgumentsType type, String name, int value, TableDefaultArgumentsType defaultType){
        this.type = type;
        this.name = name;
        this.value = value;
        this.defaultType = defaultType;
    }

    //CREATE TABLE `booo_clients`.`EMinage`
    // ( `test` INT(32) NOT NULL AUTO_INCREMENT , `top` VARCHAR(32) NOT NULL , `test` DATE NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    // PRIMARY KEY (`test`), UNIQUE (`top`)) ENGINE = InnoDB;


    public TableArgumentsType getType() {
        return type;
    }

    public void setType(TableArgumentsType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TableDefaultArgumentsType getDefaultType() {
        return defaultType;
    }

    public void setDefaultType(TableDefaultArgumentsType defaultType) {
        this.defaultType = defaultType;
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
