package me.gaegule.antiproject.account;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {

    private final DataSource dataSource;

    public AccountDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public List<Account> selectAll() {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<Account> accounts = new ArrayList<>();
        final String sql = "SELECT ID, USERNAME, AGE FROM ACCOUNT";

        try {
            conn = getConnection();
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs.next()) {
                accounts.add(new Account(rs.getLong("id"), rs.getString("username"), rs.getInt("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psmt != null) {
                try {
                    psmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        return accounts;
    }

    public Account selectAccountByUsernameAndAgeGreaterThan(List<String> params) {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        Account account = null;
        final String sql = "SELECT ID, USERNAME, AGE FROM ACCOUNT WHERE USERNAME = ? AND AGE > ?";

        try {
            conn = getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, params.get(0));
            psmt.setInt(2, Integer.parseInt(params.get(1)));
            rs = psmt.executeQuery();

            if (rs.next()) {
                account = new Account(rs.getLong("id"), rs.getString("username"), rs.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psmt != null) {
                try {
                    psmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        return account;
    }
}
