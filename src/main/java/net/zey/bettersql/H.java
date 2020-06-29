package net.zey.bettersql;

import net.zey.bettersql.arguments.TableArguments;
import net.zey.bettersql.arguments.TableArgumentsType;
import net.zey.bettersql.arguments.TableDefaultArgumentsType;
import net.zey.bettersql.help.SQLObject;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

public class H {

    /*

        This library was made by Zey,
        The objective was to create a new Save System, more easy and swift :d
        You have no right to take back, copy or steal the code of this class or the entire library.
        You have more information on how to use the library in readme.md
        Thanks, Zey.

     */

    /*

        Pay Attention !!!
        ----------------------------------------------------------------
        This class does not respect java conventions, BUT but but but but but
        this library is used to speed up the use of sql and this class is used to go faster.
        That's why I kept it with that name.

        » So thanks for not pissing me off :D :sheep: :sheep: :sheep:

     */

    public static SQLObject ob(int element){
        return new SQLObject(element);
    }

    public static SQLObject ob(String element){
        return new SQLObject(element);
    }

    public static SQLObject ob(Date element){
        return new SQLObject(element);
    }

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

    public static HashMap hash(List<String> name, List<SQLObject> values){
        HashMap<String, SQLObject> map = new HashMap<>();
        if(name.size() != values.size())return map;
        for(String n : name){
            map.put(n, values.get(name.indexOf(n)));
        }
        return map;
    }
}
