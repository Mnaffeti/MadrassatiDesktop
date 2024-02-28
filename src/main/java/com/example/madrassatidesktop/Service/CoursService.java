package com.example.madrassatidesktop.Service;

import com.example.madrassatidesktop.Entite.Cours;
import com.example.madrassatidesktop.Utils.DataSource;
import java.time.LocalDateTime;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CoursService implements IService<Cours> {

    private Statement ste;
    private Connection con;
    private static CoursService ser;

    private CoursService() {
        try {
            con = DataSource.getInstance().getCon();
            ste = con.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static CoursService getInstance() {
        if (ser == null) ser = new CoursService();
        return ser;
    }

    public void add(Cours cours) throws SQLException {
        String req = "INSERT INTO cours (idCours, LibeleCours, DateDebut, DateFin, idEnseignant) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(req);
        pstmt.setInt(1, cours.getIdCours());
        pstmt.setString(2, cours.getLibeleCours());
        pstmt.setTimestamp(3, Timestamp.valueOf(cours.getDateDebut()));
        pstmt.setTimestamp(4, Timestamp.valueOf(cours.getDateFin()));
        pstmt.setInt(5, cours.getIdEnseignant());
        pstmt.executeUpdate();
    }
    @Override
    public boolean delete(Cours cours) throws SQLException {
        String req = "DELETE FROM `cours` WHERE idCours = '" + cours.getIdCours() + "';";
        int rowsDeleted = ste.executeUpdate(req);
        return rowsDeleted > 0;
    }

    public boolean update(Cours cours) throws SQLException {
        String req = "UPDATE cours SET LibeleCours = ?, DateDebut = ?, DateFin = ?, idEnseignant = ? WHERE idCours = ?";
        PreparedStatement pstmt = con.prepareStatement(req);
        pstmt.setString(1, cours.getLibeleCours());
        pstmt.setTimestamp(2, Timestamp.valueOf(cours.getDateDebut()));
        pstmt.setTimestamp(3, Timestamp.valueOf(cours.getDateFin()));
        pstmt.setInt(4, cours.getIdEnseignant());
        pstmt.setInt(5, cours.getIdCours());
        int rowsUpdated = pstmt.executeUpdate();
        return rowsUpdated > 0;
    }

    @Override
    public Cours findById(Cours cours) throws SQLException {
        return null;
    }

    public Cours findById(int idCours) throws SQLException {
        String req = "SELECT * FROM cours WHERE idCours = ?";
        PreparedStatement pstmt = con.prepareStatement(req);
        pstmt.setInt(1, idCours);
        ResultSet res = pstmt.executeQuery();
        if (res.next()) {
            return new Cours(
                    res.getInt("idCours"),
                    res.getString("LibeleCours"),
                    res.getTimestamp("DateDebut").toLocalDateTime(), // Convert Timestamp to LocalDateTime
                    res.getTimestamp("DateFin").toLocalDateTime(),   // Convert Timestamp to LocalDateTime
                    res.getInt("idEnseignant")
            );
        }
        return null;
    }



    public List<Cours> findAll() throws SQLException {
        List<Cours> coursList = new ArrayList<>();
        String req = "SELECT * FROM `cours`;";
        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            // Convert Timestamp to LocalDateTime
            LocalDateTime dateDebut = res.getTimestamp("DateDebut") != null ? res.getTimestamp("DateDebut").toLocalDateTime() : null;
            LocalDateTime dateFin = res.getTimestamp("DateFin") != null ? res.getTimestamp("DateFin").toLocalDateTime() : null;

            Cours cours = new Cours(
                    res.getInt("idCours"),
                    res.getString("LibeleCours"),
                    dateDebut, // Use LocalDateTime
                    dateFin,   // Use LocalDateTime
                    res.getInt("idEnseignant")
            );
            coursList.add(cours);
        }
        return coursList;
    }

}
