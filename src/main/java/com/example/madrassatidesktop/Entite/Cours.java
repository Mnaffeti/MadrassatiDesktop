package com.example.madrassatidesktop.Entite;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Cours {
    private int idCours;
    private String libeleCours;
    private LocalDateTime dateDebut;
    private LocalDateTime  dateFin;
    private int idEnseignant;
    private int idModule ;

    public Cours(int idCours, String libeleCours, LocalDateTime dateDebut, LocalDateTime dateFin, int idEnseignant,int idModule) {
        this.idCours = idCours;
        this.libeleCours = libeleCours;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idEnseignant = idEnseignant;
        this.idModule=idModule;
    }
    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public String getLibeleCours() {
        return libeleCours;
    }

    public void setLibeleCours(String libeleCours) {
        this.libeleCours = libeleCours;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public int getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(int idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public int getIdModule() {
        return idModule;
    }

    public void setIdModule(int idModule) {
        this.idModule = idModule;
    }

    // Optional: Override toString() method for easier object representation
    @Override
    public String toString() {
        return "Cours{" +
                "idCours=" + idCours +
                ", libeleCours='" + libeleCours + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", idEnseignant=" + idEnseignant +
                '}';
    }


}