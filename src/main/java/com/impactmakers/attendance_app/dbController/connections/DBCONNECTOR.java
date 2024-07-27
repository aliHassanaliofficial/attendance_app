package com.impactmakers.attendance_app.dbController.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCONNECTOR {

    public static Connection connect() {
        String url = "jdbc:sqlite:C:/Users/aliha/OneDrive/Documents/NetBeansProjects/attendance_app/db/test.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return conn;
    }
}
