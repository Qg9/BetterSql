package net.zey.bettersql;

import net.zey.bettersql.arguments.TableArguments;
import net.zey.bettersql.arguments.TableArgumentsType;
import net.zey.bettersql.arguments.TableDefaultArgumentsType;

public class Help {

    /*

        This library was made by Zey,
        The objective was to create a new Save System, more easy and swift :d
        You have no right to take back, copy or steal the code of this class or the entire library.
        You have more information on how to use the library in readme.md
        Thanks, Zey.

     */

    //You can create an Tablearguments more swifty with
    public static TableArguments args(String type, String name, int value, String defaultType){
        return new TableArguments(TableArgumentsType.getByLetter(type), name, value, TableDefaultArgumentsType.getByLetter(defaultType));
    }
    public static TableArguments varArgs(String name, int value){
        return new TableArguments(TableArgumentsType.VARCHAR, name, value, TableDefaultArgumentsType.NO);
    }
    public static TableArguments intArgs(String name, int value){
        return new TableArguments(TableArgumentsType.INT, name, value, TableDefaultArgumentsType.NO);
    }
    public static TableArguments textArgs(String name, int value){
        return new TableArguments(TableArgumentsType.TEXT, name, value, TableDefaultArgumentsType.NO);
    }
}
