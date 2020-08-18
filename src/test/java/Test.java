import net.zey.bettersql.database.Database;
import net.zey.bettersql.database.SqliteDatabase;
import net.zey.bettersql.database.Table;
import net.zey.bettersql.help.Help;

public class Test {

    public static void main(String[] args){

        Database db = new SqliteDatabase("C:/Users/gq179/Desktop/dev", "test");
        Table t = db.getTable("test", Help.varArgs("hey", 50));
        t.createTable();
        t.delete().where("hey", "test").sendSql();
    }

}
