package me.gaegul.strategyparttern.template;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface ParamsStrategy {

    void setParams(PreparedStatement psmt) throws SQLException;
}
