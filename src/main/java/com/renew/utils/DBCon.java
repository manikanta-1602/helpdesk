package com.renew.utils;

import java.sql.*;
import java.io.*;
import java.util.*;

public class DBCon {
    private static volatile boolean tablesInitialized = false;

    public static Connection getDBConnection() {
        boolean useH2 = "true".equalsIgnoreCase(System.getenv("USE_H2"))
                     || System.getenv("RENDER") != null
                     || "H2".equalsIgnoreCase(System.getenv("DB_TYPE"));

        if (!useH2) {
            try {
                Class.forName("oracle.jdbc.OracleDriver");
                String url = System.getenv("ORACLE_URL") != null ? System.getenv("ORACLE_URL") : "jdbc:oracle:thin:@localhost:1522:XE";
                String uname = System.getenv("ORACLE_USER") != null ? System.getenv("ORACLE_USER") : "system";
                String pwd = System.getenv("ORACLE_PASSWORD") != null ? System.getenv("ORACLE_PASSWORD") : "svsm";
                return DriverManager.getConnection(url, uname, pwd);
            } catch (Exception e) {
                System.out.println("Oracle connection unavailable, falling back to embedded H2 database: " + e.getMessage());
            }
        }

        try {
            Class.forName("org.h2.Driver");
            String url = "jdbc:h2:./data/renew;MODE=Oracle;DEFAULT_NULL_ORDERING=HIGH;DB_CLOSE_DELAY=-1";
            Connection con = DriverManager.getConnection(url, "sa", "");
            initH2Schema(con);
            return con;
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize database connection", e);
        }
    }

    private static synchronized void initH2Schema(Connection con) {
        if (tablesInitialized) return;
        try (Statement st = con.createStatement()) {
            st.execute("CREATE SEQUENCE IF NOT EXISTS users_seq START WITH 1 INCREMENT BY 1");
            st.execute("CREATE TABLE IF NOT EXISTS Users (" +
                       "userId NUMBER PRIMARY KEY, " +
                       "name VARCHAR2(100) NOT NULL, " +
                       "email VARCHAR2(100) UNIQUE NOT NULL, " +
                       "uname VARCHAR2(100) UNIQUE NOT NULL, " +
                       "password_hash VARCHAR2(255) NOT NULL)");
            st.execute("CREATE SEQUENCE IF NOT EXISTS reminders_seq START WITH 1 INCREMENT BY 1");
            st.execute("CREATE TABLE IF NOT EXISTS Reminders (" +
                       "reminderId NUMBER PRIMARY KEY, " +
                       "userId NUMBER NOT NULL, " +
                       "name VARCHAR2(255) NOT NULL, " +
                       "notes VARCHAR2(1000), " +
                       "reminderDate TIMESTAMP NOT NULL)");
            tablesInitialized = true;
            System.out.println("H2 Database schema verified and initialized!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
