package me.gaegul.methodtemplatepattern.template;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class DataSourceTemplate {

    private final DataSource dataSource;

    public DataSourceTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public <T> T select(String sql, List<String> params) {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            psmt = conn.prepareStatement(sql);

            this.setParams(psmt, params);

            rs = psmt.executeQuery();

            return this.setResult(rs);
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
        return null;
    }

    protected abstract void setParams(PreparedStatement psmt, List<String> params) throws SQLException;

    protected abstract <T> T setResult(ResultSet rs) throws SQLException;

}
