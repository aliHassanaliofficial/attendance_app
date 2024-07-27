package com.impactmakers.attendance_app.dbController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import com.impactmakers.attendance_app.dbController.connections.ConnectionDetector;
import com.impactmakers.attendance_app.dbController.connections.DBCONNECTOR;

public class RemoveStu extends DBCONNECTOR {

    Scanner sc = new Scanner(System.in);
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int logicistics = 0;

    public int getLogistics() {
        return logicistics;
    }

    public void setLogistics(int logicistics) {
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

    public void deleteStudent(int id) {
        ConnectionDetector.connect();
        String sql = "DELETE FROM students_data WHERE id = ?";
        try (Connection conn = DBCONNECTOR.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student with ID " + id + " deleted successfully.");
                logicistics = 1;
            } else {
                System.out.println("Student with ID " + id + " not found.");
            logicistics = -1;
            }
        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());

        }
    }
}
