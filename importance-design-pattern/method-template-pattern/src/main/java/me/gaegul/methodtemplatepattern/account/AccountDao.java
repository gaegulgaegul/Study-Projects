package me.gaegul.methodtemplatepattern.account;

import javax.sql.DataSource;
import java.util.List;

public class AccountDao {

    private final DataSource dataSource;

    public AccountDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Account> findAll() {
        final String sql = "SELECT ID, USERNAME, AGE FROM ACCOUNT";
        AccountTemplate.SelectAll selectAll = new AccountTemplate.SelectAll(dataSource);
        List<Account> accounts = selectAll.select(sql, null);
        return accounts;
    }

    public Account findByUsername(String username) {
        final String sql = "SELECT ID, USERNAME, AGE FROM ACCOUNT WHERE USERNAME = ?";
        final List<String> params = List.of(username);
        AccountTemplate.SelectByUsername selectByUsername = new AccountTemplate.SelectByUsername(dataSource);
        Account account = selectByUsername.select(sql, params);
        return account;
    }
}
