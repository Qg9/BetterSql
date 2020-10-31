package fr.better.sql.help;

import fr.better.sql.exception.BetterSqlException;

import java.util.List;

public interface Result {

    List<Object> get() throws BetterSqlException;
    List<List<Object>> nextResult();

}
