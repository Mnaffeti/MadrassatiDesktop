package com.example.madrassatidesktop.Service;



import com.example.madrassatidesktop.Entite.Formation;
import com.example.madrassatidesktop.Utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FormationService implements IService<Formation> {

    private Statement ste;

    private static FormationService ser;

    private FormationService() {
        try {
            Connection con1 = DataSource.getInstance().getCon();
            ste = con1.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static FormationService getInstance() {
        if (ser == null) ser = new FormationService();
        return ser;
    }

    @Override
    public void add(Formation formation) throws SQLException {
        String req = "INSERT INTO `formation` (`nom`, `sujet`, `description`, `idCategorie`) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = ste.getConnection().prepareStatement(req, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, formation.getNom());
            pstmt.setString(2, formation.getSujet());
            pstmt.setString(3, formation.getDescription());
            pstmt.setInt(4, formation.getIdCategorie());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    formation.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Failed to retrieve the generated id.");
                }
            }
        }
    }

    @Override
    public boolean delete(Formation formation) throws SQLException {
        String req = "DELETE FROM `formation` WHERE id=?";
        try (PreparedStatement pstmt = ste.getConnection().prepareStatement(req)) {
            pstmt.setInt(1, formation.getId());
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        }
    }

    @Override
    public boolean update(Formation formation) throws SQLException {
        String req = "UPDATE `formation` SET `nom`=?, `sujet`=?, `description`=?, `idCategorie`=? WHERE id=?";
        try (PreparedStatement pstmt = ste.getConnection().prepareStatement(req)) {
            pstmt.setString(1, formation.getNom());
            pstmt.setString(2, formation.getSujet());
            pstmt.setString(3, formation.getDescription());
            pstmt.setInt(4, formation.getIdCategorie());
            pstmt.setInt(5, formation.getId());
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    @Override
    public Formation findById(Formation formation) throws SQLException {
        String req = "SELECT * FROM `formation` WHERE id=?";
        try (PreparedStatement pstmt = ste.getConnection().prepareStatement(req)) {
            pstmt.setInt(1, formation.getId());
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                return new Formation(
                        res.getInt("id"),
                        res.getString("nom"),
                        res.getString("sujet"),
                        res.getString("description"),
                        res.getInt("idCategorie")
                );
            }
        }
        return null;
    }

    @Override
    public Formation findById(int t) throws SQLException {
        return null;
    }

    @Override
    public List<Formation> findAll() throws SQLException {
        List<Formation> formations = new ArrayList<>();
        String req = "SELECT * FROM `formation`";
        try (ResultSet res = ste.executeQuery(req)) {
            while (res.next()) {
                Formation formation = new Formation(
                        res.getInt("id"),
                        res.getString("nom"),
                        res.getString("sujet"),
                        res.getString("description"),
                        res.getInt("idCategorie")
                );
                formations.add(formation);
            }
        }
        return formations;
    }

}
