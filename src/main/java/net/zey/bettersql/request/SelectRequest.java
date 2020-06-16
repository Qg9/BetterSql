package net.zey.bettersql.request;

import net.zey.bettersql.arguments.TableArguments;
import net.zey.bettersql.condition.*;
import net.zey.bettersql.help.SQLObject;
import net.zey.bettersql.database.Table;
import net.zey.bettersql.help.SqlResult;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectRequest extends Request{

    public SelectRequest(Table table, StringBuilder sql) {
        super(table, sql);
    }

    @Override
    public SqlResult sendSql() {
        try {
            if (getCondition() != null) {
                getSql().append(getCondition().getAdding());
            }
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection conn = getTable().getDatabase().getConnection();
            PreparedStatement p = conn.prepareStatement(getSql().toString());

            if (getCondition() != null) {
                if (getCondition() instanceof RepCondition) {
                    RepCondition cond = (RepCondition) getCondition();
                    set(1, cond.getObj(), p);
                }
            }

            ResultSet r = p.executeQuery();

            List<SQLObject> all = new ArrayList<>();
            Table t = getTable();
            while (r.next()) {
                for (TableArguments tb : t.getArgs()) {
                    SQLObject o = get(tb, r);
                    all.add(o);
                }
            }
            r.close();
            p.close();
            return new SqlResult(all);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return new SqlResult(new ArrayList<>());
    }

}
