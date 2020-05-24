package net.zey.test;

import net.zey.bettersql.H;
import net.zey.bettersql.database.Database;
import net.zey.bettersql.database.Table;


import java.util.Arrays;

public class Main {

    public static void main(String[] args){

        Database db = new Database("C:/Users/gq179/Desktop", "storage");
        db.connect();

        Table t = db.getTable("city", Arrays.asList(H.args("va", "name", 32, "no"), H.args("in", "age", 1024, "no"), H.args("te", "bio", 1024, "no")));
        t.createTable();

        t.add(Arrays.asList(H.ob("Gabriel"), H.ob(14), H.ob("Sac a foutre !"))).sendSql();

    }
}
