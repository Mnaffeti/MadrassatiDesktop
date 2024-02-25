package com.example.madrassatidesktop.Entite;

public class Enseignant {
    private int idEnseignant;
    private String nom;
    private String prenom;
    private String specialite;
    private int idUtilisateur;

    // Constructor
    public Enseignant(int idEnseignant, String nom, String prenom, String specialite, int idUtilisateur) {
        this.idEnseignant = idEnseignant;
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        this.idUtilisateur = idUtilisateur;
    }

    // Getters and Setters
    public int getId() {
        return idEnseignant;
    }

    public void setId(int idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
}