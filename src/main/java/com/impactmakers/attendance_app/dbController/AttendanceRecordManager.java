package com.impactmakers.attendance_app.dbController;

import java.sql.*;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import com.impactmakers.attendance_app.dbController.connections.DBCONNECTOR;
import com.impactmakers.attendance_app.dbController.connections.ConnectionDetector;

public class AttendanceRecordManager extends DBCONNECTOR {

    public String url = "jdbc:sqlite:C:/Users/aliha/OneDrive/Documents/NetBeansProjects/attendance_app/db/test.db";
    boolean isEntered;

    public boolean isIsEntered() {
        return isEntered;
    }

    public void setIsEntered(boolean isEntered) {
        this.isEntered = isEntered;
    }

    public int logicistics; //*GUI COLOUR*//

    public int getLogicistics() {
        return logicistics;
    }

    public void setLogicistics(int logicistics) {
        this.logicistics = logicistics;
    }

    public boolean isStudentRegistered(int studentId) {
        ConnectionDetector.connect();   
        try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt = conn.prepareStatement(
                "SELECT COUNT(*) FROM students_data WHERE id = ?")) {
            pstmt.setInt(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void recordAttendance(int studentId, String classCode, int minutes, int status) {
        if (!isStudentRegistered(studentId)) {
            System.out.println("Student not registered. Cannot record attendance.");
            return;
        }
        try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO attendance_records (student_id, attendance_date, refrence_code, minutes, status) VALUES (?, ?, ?, ?, ?)")) {
            pstmt.setInt(1, studentId);
            LocalDate currentDate = LocalDate.now().plusDays(1);
            pstmt.setDate(2, Date.valueOf(currentDate));
            pstmt.setString(3, classCode);
            pstmt.setInt(4, minutes);
            pstmt.setInt(5, status);
            pstmt.executeUpdate(); // WRITE CHANGES
            logicistics = 1;   
            System.out.println("Attendance recorded successfully for student ID: " + studentId + " on " + currentDate);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            logicistics = -1;
        }
    }
}
