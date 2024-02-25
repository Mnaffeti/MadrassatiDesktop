package com.example.madrassatidesktop.Entite;

public class Etudiant {
    private int id;
    private String nom;
    private String prenom;
    private int age;
    private String nomUtilisateur;
    private String motDePasse;
    private int idFormation;
    private int idUtilisateur;

    public Etudiant(int id, String nom, String prenom, int age, String nomUtilisateur, String motDePasse, int idFormation, int idUtilisateur) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
        this.idFormation = idFormation;
        this.idUtilisateur = idUtilisateur;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getAge() {
        return age;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public int getIdFormation() {
        return idFormation;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setIdFormation(int idFormation) {
        this.idFormation = idFormation;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
}

