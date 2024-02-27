package com.example.madrassatidesktop.Entite;

public class Utilisateur  {
    private int idUtilisateur ;

    private String email;
    private String motDePasse;
    public Utilisateur() {
    }


    public Utilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Utilisateur(String email,String motDePasse) {
        //this.idUtilisateur = idUtilisateur;
        this.email = email;
        this.motDePasse = motDePasse;

    }


    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getmotDePasse() {
        return motDePasse;
    }

    public void setmotDePasee(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    public int getId() {
        return idUtilisateur;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "idUtilisateur=" + idUtilisateur +
                ", email='" + email + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                '}';
    }
}