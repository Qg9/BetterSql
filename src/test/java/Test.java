import net.zey.bettersql.database.Database;
import net.zey.bettersql.database.SqliteDatabase;
import net.zey.bettersql.database.Table;
import net.zey.bettersql.help.Help;

public class Test {

    public static void main(String[] args){

        Database db = new SqliteDatabase("C:/src/dev", "test");
        Table t = db.getTable("test", Help.varArgs("hey", 50), Help.intArgs("i", 5));
        t.createTable();

        t.add("test", 50).sendSql();

        t.delete().where("hey", "test").sendSql();
    }

}
