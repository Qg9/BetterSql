package fr.better.sql.help;

import fr.better.sql.database.Database;
import fr.better.sql.database.Table;
import fr.better.sql.database.arguments.TableArguments;
import fr.better.sql.database.arguments.TableArgumentsType;

public class Help {

    public static TableArguments args(String type, String name, int value){
        return new TableArguments(TableArgumentsType.getByLetter(type), name, value);
    }

    public static TableArguments varArgs(String name, int value){
        return new TableArguments(TableArgumentsType.VARCHAR, name, value);
    }

    public static TableArguments intArgs(String name, int value){
        return new TableArguments(TableArgumentsType.INT, name, value);
    }

    public static TableArguments intArgs(String name){
        return new TableArguments(TableArgumentsType.INT, name, 0);
    }

    public static TableArguments dateArgs(String name){
        return new TableArguments(TableArgumentsType.DATE, name, 0);
    }

    public static TableArguments boolArgs(String name){
        return new TableArguments(TableArgumentsType.BOOLEAN, name, 0);
    }

    public static TableArguments textArgs(String name, int value){
        return new TableArguments(TableArgumentsType.TEXT, name, value);
    }
}
