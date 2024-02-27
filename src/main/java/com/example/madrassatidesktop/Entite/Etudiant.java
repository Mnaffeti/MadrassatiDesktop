package com.example.madrassatidesktop.Entite;

public class Etudiant {

    private String nom;
    private String prenom;
    private int age;
    private int idFormation;
    private int idUtilisateur;

    public Etudiant( String nom, String prenom, int age ,int idFormation, int idUtilisateur) {

        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.idFormation = idFormation;
        this.idUtilisateur = idUtilisateur;
    }

    // Getters

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getAge() {
        return age;
    }
    public int getIdFormation() {
        return idFormation;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    // Setters


    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void setIdFormation(int idFormation) {
        this.idFormation = idFormation;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
}

