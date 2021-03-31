package me.gaegul.strategyparttern.account;

import me.gaegul.strategyparttern.template.DataSourceTemplate;
import me.gaegul.strategyparttern.template.ParamsStrategy;
import me.gaegul.strategyparttern.template.ResultSetStrategy;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDao extends DataSourceTemplate {

    public AccountDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<Account> findAll() {
        return this.select("SELECT ID, USERNAME, AGE FROM ACCOUNT", null,
                rs -> {
                    List<Account> accounts = new ArrayList<>();
                    while (rs.next()) {
                        accounts.add(getAccountFromResultSet(rs));
                    }
                    return accounts;
                });
    }

    public Account findByUsername(String username) {
        return this.select(
                "SELECT ID, USERNAME, AGE FROM ACCOUNT WHERE USERNAME = ?",
                psmt -> psmt.setString(1, username),
                rs -> {
                    if (rs.next()) {
                        return getAccountFromResultSet(rs);
                    }
                    return null;
                });
    }

    private Account getAccountFromResultSet(ResultSet rs) throws SQLException {
        return new Account(rs.getLong("id"), rs.getString("username"), rs.getInt("age"));
    }

}
