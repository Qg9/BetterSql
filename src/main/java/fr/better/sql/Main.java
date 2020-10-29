package fr.better.sql;

import fr.better.sql.database.Database;
import fr.better.sql.database.Table;
import fr.better.sql.help.Help;

public class Main {

    public static void main(String[] args){

        Database db = new Database("C:/Users/33645/Desktop", "storage");
        Table t = db.getTableAndCreateIt("user", Help.varArgs("user", 12), Help.intArgs("age"));
        t.add("kevin", 12).execute();
    }
}
