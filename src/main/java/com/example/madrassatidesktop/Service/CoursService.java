package com.example.madrassatidesktop.Service;

import com.example.madrassatidesktop.Entite.Cours;
import com.example.madrassatidesktop.Utils.DataSource;

import java.sql.*;
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

    @Override
    public void add(Cours cours) throws SQLException {
        String req = "INSERT INTO cours (nom, description, duree, coefficient) VALUES ('" + cours.getNom() + "', '" + cours.getDescription() + "', '" + cours.getDuree() + "', '" + cours.getCoefficient() + "');";
        ste.executeUpdate(req);
    }

    @Override
    public boolean delete(Cours cours) throws SQLException {
        String req = "DELETE FROM `cours` WHERE idCours='" + cours.getIdCours() + "';";
        int rowsDeleted = ste.executeUpdate(req);
        return rowsDeleted > 0;
    }

    @Override
    public boolean update(Cours cours) throws SQLException {
        String req = "UPDATE `cours` SET `nom`='" + cours.getNom() + "', `description`='" + cours.getDescription() + "', `duree`='" + cours.getDuree() + "', `coefficient`='" + cours.getCoefficient() + "' WHERE idCours='" + cours.getIdCours() + "';";
        int rowsUpdated = ste.executeUpdate(req);
        return rowsUpdated > 0;
    }

    @Override
    public Cours findById(Cours cours) throws SQLException {
        // Implement method based on your application's requirements
        return null; // Placeholder for actual implementation
    }

    @Override
    public List<Cours> findAll() throws SQLException {
        List<Cours> coursList = new ArrayList<>();
        String req = "SELECT * FROM `cours`;";
        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            int idCours = res.getInt("idCours");
            String nom = res.getString("nom");
            String description = res.getString("description");
            int duree = res.getInt("duree");
            double coefficient = res.getDouble("coefficient");
            Cours cours = new Cours(idCours, nom, description, duree, coefficient);
            coursList.add(cours);
        }
        return coursList;
    }
}
