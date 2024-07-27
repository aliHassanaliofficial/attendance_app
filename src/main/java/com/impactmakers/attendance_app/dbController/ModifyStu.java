package com.impactmakers.attendance_app.dbController;

import com.impactmakers.attendance_app.dbController.connections.DBCONNECTOR;
import com.impactmakers.attendance_app.dbController.connections.ConnectionDetector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ModifyStu extends ConnectionDetector {

    Scanner sc = new Scanner(System.in);
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int logicistics;

    public int getLogicistics() {
        return logicistics;
    }

    public void setLogicistics(int logicistics) {
        this.logicistics = logicistics;
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
    public void update(String name, int field, double gpa, int academic_year, int hours) {
        ConnectionDetector.connect();
        String sql = "UPDATE students_data SET name = ?, field = ?, gpa = ?, academic_year = ?, hours = ? WHERE id = ?";

        try (Connection conn = DBCONNECTOR.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (!conn.isValid(0)) {
                throw new SQLException("Invalid database connection");
            }
            pstmt.setString(1, name);
            pstmt.setInt(2, field);
            pstmt.setDouble(3, gpa);
            pstmt.setInt(4, academic_year);
            pstmt.setInt(5, hours);
            pstmt.setInt(6, id);

            System.out.println("SQL Statement: " + pstmt);
            System.out.println("Updating ID: " + id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
            logicistics = 1;    
            }
            else if (rowsAffected == 0) {
                System.out.println("No rows were updated. Check if the ID exists.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating student data: " + e.getMessage());
            logicistics = -1;
        }
    }
}
