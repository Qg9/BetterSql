package net.zey.test;

import net.zey.bettersql.H;
import net.zey.bettersql.arguments.TableArguments;
import net.zey.bettersql.arguments.TableArgumentsType;
import net.zey.bettersql.arguments.TableDefaultArgumentsType;
import net.zey.bettersql.database.Database;
import net.zey.bettersql.database.SQLObject;
import net.zey.bettersql.database.Table;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args){

        Database db = new Database("C:/Users/gq179/Desktop", "storage");
        db.connect();

        List<TableArguments> tbArgs = new ArrayList<>();
        tbArgs.add(new TableArguments(TableArgumentsType.VARCHAR, "name", 32, TableDefaultArgumentsType.NO));
        tbArgs.add(new TableArguments(TableArgumentsType.INT, "age", 32, TableDefaultArgumentsType.NO));
        tbArgs.add(new TableArguments(TableArgumentsType.VARCHAR, "bio", 32, TableDefaultArgumentsType.DEFAULT_VALUE).setData("Default Biographie Set !"));

        Table t = db.getTable("city", Arrays.asList(H.args("va", "name", 32, "no"), H.args("in", "age", 1024, "no"), H.args("te", "bio", 1024, "no")));
        t.createTable();

        t.add(Arrays.asList(H.ob("Gabriel"), H.ob(14), H.ob("Sac a foutre !"))).sendSql();

        HashMap<String, SQLObject> toUp = new HashMap<>();
        toUp.put("age", new SQLObject(15));
        toUp.put("bio", new SQLObject("My new biographie"));
        t.update(toUp).where("name", new SQLObject("Gabriel")).sendSql();

        List<SQLObject> all = t.select().where("name", H.ob("Zey")).sendSql();

        String bio;
        int age;

        if(all.isEmpty()){
            //Do when player isn't in the database
            //Exemple :
            t.add(Arrays.asList(H.ob("Zey"), H.ob(14), H.ob("A default Biographie"))).sendSql();
            age = 14;
            bio = "A default Biographie";
        }else{
            //If the player was in the database
            age = all.get(1).getInt();
            bio = all.get(2).getString();
        }
    }
}
