package com.sample.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcHelper<JBDC> {

    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String url = "jdbc:sqlserver://DESKTOP-S9HLI82\\SQLEXPRESS:1433;databaseName=QLNhaTro";
    private static String user = "sa";
    private static String pass = "123";

    static {
        try {
            Connection cnt = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static PreparedStatement prepareStatement(String sql, Object... args) throws SQLException {
        PreparedStatement ptsm = null;
        Connection cnt = DriverManager.getConnection(url, user, pass);
        if (sql.trim().startsWith("{")) {
            ptsm = cnt.prepareCall(sql);
        } else {
            ptsm = cnt.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            ptsm.setObject(i + 1, args[i]);
        }
        return ptsm;
    }

    ;

    public static void executeUpdate(String sql, Object... args) {
        try {
            PreparedStatement ptsm = prepareStatement(sql, args);
            try {
                ptsm.executeUpdate();
            } finally {
                try {
                    ptsm.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        };
    }

    public static ResultSet executeQuery(String sql, Object... args) {
        try {
            PreparedStatement ptsm = prepareStatement(sql, args);
            return ptsm.executeQuery();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
