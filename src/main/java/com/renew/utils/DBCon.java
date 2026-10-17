package com.renew.utils;

import java.sql.*;
import java.io.*;
import java.util.*;

public class DBCon {
    public static Connection getDBConnection() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Oracle Driver Loaded!");
            String url = "jdbc:oracle:thin:@localhost:1522:XE";
            String uname = "system";
            String pwd = "svsm";
            return DriverManager.getConnection(url, uname, pwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
