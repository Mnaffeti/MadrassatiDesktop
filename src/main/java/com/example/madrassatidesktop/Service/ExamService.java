package com.example.madrassatidesktop.Service;

import com.example.madrassatidesktop.Entite.CourseMark;
import com.example.madrassatidesktop.Entite.Exam;
import com.example.madrassatidesktop.Utils.DataSource;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExamService {
    private Statement ste;
    private Connection con;
    private static ExamService ser;

    private ExamService() {
        try {
            con = DataSource.getInstance().getCon();
            ste = con.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ExamService getInstance() {
        if (ser == null) ser = new ExamService();
        return ser;
    }

    public void addExam(Exam exam) throws SQLException {
        String req = "INSERT INTO exam (idUtilisateur, idCours, marks, exam_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(req)) {
            pstmt.setInt(1, exam.getIdUtilisateur());
            pstmt.setInt(2, exam.getIdCours());
            pstmt.setInt(3, exam.getMarks());
            pstmt.setDate(4, Date.valueOf(exam.getExamDate()));
            pstmt.executeUpdate();
        }
    }

    public int getNumberOfExamsByUserId(int idUtilisateur) throws SQLException {
        String sql = "SELECT COUNT(*) AS total_exams FROM exam WHERE idUtilisateur = ?";
        int totalExams = 0;
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, idUtilisateur);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    totalExams = rs.getInt("total_exams");
                }
            }
        }
        return totalExams;
    }
    public float getAVGOfExamsByUserId(int idUtilisateur) throws SQLException {
        String sql = "SELECT AVG(marks) AS avg_marks FROM exam WHERE idUtilisateur = ?";
        float avg_marks = 0;
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, idUtilisateur);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    avg_marks = rs.getFloat("avg_marks");
                }
            }
        }
        return avg_marks;
    }
    public List<CourseMark> getCourseMarksByUserId(int idUtilisateur) throws SQLException {
        List<CourseMark> courseMarks = new ArrayList<>();
        String sql = "SELECT c.LibeleCours, e.marks " +
                "FROM exam e " +
                "JOIN cours c ON e.idCours = c.idCours " +
                "WHERE e.idUtilisateur = ?"; // Add WHERE clause here

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, idUtilisateur); // Set idUtilisateur for the query

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String libeleCours = rs.getString("LibeleCours");
                    int marks = rs.getInt("marks");
                    courseMarks.add(new CourseMark(libeleCours, marks));
                }
            }
        }
        return courseMarks;
    }

    public List<Exam> findAllExams() throws SQLException {
        List<Exam> exams = new ArrayList<>();
        String req = "SELECT * FROM exam";
        try (ResultSet rs = ste.executeQuery(req)) {
            while (rs.next()) {
                Exam exam = new Exam(
                        rs.getInt("exam_id"),
                        rs.getInt("idUtilisateur"),
                        rs.getInt("idCours"),
                        rs.getInt("marks"),
                        rs.getDate("exam_date").toLocalDate()
                );
                exams.add(exam);
            }
        }
        return exams;
    }
}
