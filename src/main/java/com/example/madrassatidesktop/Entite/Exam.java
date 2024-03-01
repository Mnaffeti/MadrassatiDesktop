package com.example.madrassatidesktop.Entite;

import java.time.LocalDate;

public class Exam {
    private int examId;
    private int idUtilisateur;
    private int idCours;
    private int marks;
    private LocalDate examDate;

    // Constructor
    public Exam(int examId, int idUtilisateur, int idCours, int marks, LocalDate examDate) {
        this.examId = examId;
        this.idUtilisateur = idUtilisateur;
        this.idCours = idCours;
        this.marks = marks;
        this.examDate = examDate;
    }

    // Getters and setters
    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }
}
