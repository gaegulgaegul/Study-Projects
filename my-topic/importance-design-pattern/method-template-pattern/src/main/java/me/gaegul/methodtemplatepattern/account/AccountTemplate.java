package me.gaegul.methodtemplatepattern.account;

import me.gaegul.methodtemplatepattern.template.DataSourceTemplate;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountTemplate {

    public static class SelectAll extends DataSourceTemplate {

        public SelectAll(DataSource dataSource) {
            super(dataSource);
        }

        @Override
        protected void setParams(PreparedStatement psmt, List<String> params) {
        }

        @Override
        protected List<Account> setResult(ResultSet rs) throws SQLException {
            List<Account> accounts = new ArrayList<>();
            while (rs.next()) {
                accounts.add(new Account(rs.getLong("id"), rs.getString("username"), rs.getInt("age")));
            }
            return accounts;
        }
    }

    public static class SelectByUsername extends DataSourceTemplate {

        public SelectByUsername(DataSource dataSource) {
            super(dataSource);
        }

        @Override
        protected void setParams(PreparedStatement psmt, List<String> params) throws SQLException {
            psmt.setString(1, params.get(0));
        }

        @Override
        protected Account setResult(ResultSet rs) throws SQLException {
            if (rs.next()) {
                return new Account(rs.getLong("id"), rs.getString("username"), rs.getInt("age"));
            }
            return null;
        }
    }

}
