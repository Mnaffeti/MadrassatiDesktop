package com.example.madrassatidesktop.Entite;

import java.util.Objects;

public class Formation {
    int id;
    String nom;
    String sujet;
    String description;
    Difficulte difficulte;
    int duree;
    int idCategorie;

    public Formation() {
    }

    public Formation(String nom, String sujet, String description) {
        this.nom = nom;
        this.sujet = sujet;
        this.description = description;
    }

    public Formation(int id, String nom, String sujet, String description) {
        this.id = id;
        this.nom = nom;
        this.sujet = sujet;
        this.description = description;
    }

    public Formation(String nom, String sujet, String description, int idCategorie) {
        this.nom = nom;
        this.sujet = sujet;
        this.description = description;
        this.idCategorie = idCategorie;
    }

    public Formation(int id, String nom, String sujet, String description, int idCategorie) {
        this.id = id;
        this.nom = nom;
        this.sujet = sujet;
        this.description = description;
        this.idCategorie = idCategorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Formation formation = (Formation) o;
        return id == formation.id && idCategorie == formation.idCategorie && Objects.equals(nom, formation.nom) && Objects.equals(sujet, formation.sujet) && Objects.equals(description, formation.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, sujet, description, idCategorie);
    }

    @Override
    public String toString() {
        return "Formation{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", sujet='" + sujet + '\'' +
                ", description='" + description + '\'' +
                ", idCategorie=" + idCategorie +
                '}';
    }
}
