package net.zey.bettersql;

import net.zey.bettersql.arguments.TableArguments;
import net.zey.bettersql.arguments.TableArgumentsType;
import net.zey.bettersql.arguments.TableDefaultArgumentsType;
import net.zey.bettersql.database.SQLObject;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

public class H {

    public static SQLObject ob(int i){
        return new SQLObject(i);
    }

    public static SQLObject ob(String i){
        return new SQLObject(i);
    }

    public static SQLObject ob(Date i){
        return new SQLObject(i);
    }

    public static TableArguments args(String type, String name, int value, String defaultType){
        return new TableArguments(TableArgumentsType.getByLetter(type), name, value, TableDefaultArgumentsType.getByLetter(defaultType));
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
