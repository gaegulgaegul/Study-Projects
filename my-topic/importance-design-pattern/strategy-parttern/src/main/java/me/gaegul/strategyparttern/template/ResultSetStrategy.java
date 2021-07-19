package me.gaegul.strategyparttern.template;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultSetStrategy<T> {

    T setResultSet(ResultSet rs) throws SQLException;

}
