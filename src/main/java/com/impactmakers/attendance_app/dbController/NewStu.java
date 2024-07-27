
package com.impactmakers.attendance_app.dbController;

import com.impactmakers.attendance_app.dbController.connections.DBCONNECTOR;
import com.impactmakers.attendance_app.dbController.connections.ConnectionDetector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class NewStu extends ConnectionDetector {
    Scanner sc = new Scanner(System.in);
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Update data of a new_students specified by the id
     * 
     * @param id
     * @param name
     * @param field
     * @param gpa
     * @param academic_year
     * @param hours
     */
    
    public int logicistics;

    public int getLogicistics() {
        return logicistics;
    }

    public void setLogicistics(int logicistics) {
        this.logicistics = logicistics;
    }
    
    public void insert(int id, String name) {
        ConnectionDetector.connect();
        String sql = "INSERT INTO students_data (id, name) VALUES (?, ?)";

        try (Connection conn = DBCONNECTOR.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (!conn.isValid(0)) {
                throw new SQLException("Invalid database connection");
            }

            pstmt.setInt(1, id);
            pstmt.setString(2, name);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("New student data inserted successfully!");
                logicistics = 1 ;
            } else {
                System.out.println("Error inserting student data.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            logicistics = -1 ;
        }
    }
}
