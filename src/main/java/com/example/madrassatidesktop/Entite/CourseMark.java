package com.example.madrassatidesktop.Entite;

public class CourseMark {
    private String libeleCours;
    private int marks;

    public CourseMark(String libeleCours, int marks) {
        this.libeleCours = libeleCours;
        this.marks = marks;
    }

    // Getters
    public String getLibeleCours() {
        return libeleCours;
    }

    public int getMarks() {
        return marks;
    }

    // Setters if necessary
}
