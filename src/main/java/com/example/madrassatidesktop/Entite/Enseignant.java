package com.example.madrassatidesktop.Entite;

public class Enseignant {

    private String nom;
    private String prenom;
    private String specialite;
    private int idUtilisateur;

    // Constructor
    public Enseignant(String nom, String prenom, String specialite, int idUtilisateur) {

        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        this.idUtilisateur = idUtilisateur;
    }

    // Getters and Setters




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