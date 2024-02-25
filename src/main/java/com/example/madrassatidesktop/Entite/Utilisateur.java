package com.example.madrassatidesktop.Entite;

public class Utilisateur  {
    private int idUtilisateur ;

    private String nomUtilisateur;
    private String email;
    private String motDePasse;
    private String role ;

    public Utilisateur() {
    }


    public Utilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Utilisateur(int idUtilisateur,String nomUtilisateur, String email, String motDePasse, String role) {
        this.idUtilisateur = idUtilisateur;
        this.nomUtilisateur = nomUtilisateur;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role=role ;
    }





    public String getnomUtilisateur() {
        return nomUtilisateur;
    }

    public void setnomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return idUtilisateur;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "idUtilisateur=" + idUtilisateur +
                ", nomUtilisateur='" + nomUtilisateur + '\'' +
                ", email='" + email + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}