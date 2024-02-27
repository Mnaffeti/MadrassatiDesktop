package com.example.madrassatidesktop.Entite;

public class Cours {

    // Attributes of the Cours class
    private int idCours;
    private String nom;
    private String description;
    private int duree; // Duration in hours
    private double coefficient; // Coefficient for grading

    // Default constructor
    public Cours() {
    }

    // Parameterized constructor
    public Cours(int idCours, String nom, String description, int duree, double coefficient) {
        this.idCours = idCours;
        this.nom = nom;
        this.description = description;
        this.duree = duree;
        this.coefficient = coefficient;
    }

    // Getters and Setters
    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    // toString method to display information about the course
    @Override
    public String toString() {
        return "Cours{" +
                "idCours=" + idCours +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", duree=" + duree +
                ", coefficient=" + coefficient +
                '}';
    }
}
