package com.example.madrassatidesktop.Service;



import com.example.madrassatidesktop.Entite.Etudiant;
import com.example.madrassatidesktop.Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantService implements IService<Etudiant> {

    private Connection con;
    private static EtudiantService ser;

    private EtudiantService() {
        con = DataSource.getInstance().getCon();
    }

    public static EtudiantService getInstance() {
        if (ser == null) ser = new EtudiantService();
        return ser;
    }

    @Override
    public void add(Etudiant etudiant) throws SQLException {
        String req = "INSERT INTO etudiant (nom, prenom, age, nomUtilisateur, motDePasse, idFormation, idUtilisateur) VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement pstmt = con.prepareStatement(req)) {
            pstmt.setString(1, etudiant.getNom());
            pstmt.setString(2, etudiant.getPrenom());
            pstmt.setInt(3, etudiant.getAge());
            pstmt.setString(4, etudiant.getNomUtilisateur());
            pstmt.setString(5, etudiant.getMotDePasse());
            pstmt.setInt(6, etudiant.getIdFormation());
            pstmt.setInt(7, etudiant.getIdUtilisateur());
            pstmt.executeUpdate();
        }
    }

    @Override
    public boolean delete(Etudiant etudiant) throws SQLException {
        String req = "DELETE FROM etudiant WHERE id = ?;";
        try (PreparedStatement pstmt = con.prepareStatement(req)) {
            pstmt.setInt(1, etudiant.getId());
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        }
    }

    @Override
    public boolean update(Etudiant etudiant) throws SQLException {
        String req = "UPDATE etudiant SET nom = ?, prenom = ?, age = ?, nomUtilisateur = ?, motDePasse = ?, idFormation = ?, idUtilisateur = ? WHERE id = ?;";
        try (PreparedStatement pstmt = con.prepareStatement(req)) {
            pstmt.setString(1, etudiant.getNom());
            pstmt.setString(2, etudiant.getPrenom());
            pstmt.setInt(3, etudiant.getAge());
            pstmt.setString(4, etudiant.getNomUtilisateur());
            pstmt.setString(5, etudiant.getMotDePasse());
            pstmt.setInt(6, etudiant.getIdFormation());
            pstmt.setInt(7, etudiant.getIdUtilisateur());
            pstmt.setInt(8, etudiant.getId());
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    @Override
    public Etudiant findById(Etudiant etudiant) throws SQLException {
        return null;
    }


    @Override
    public List<Etudiant> findAll() throws SQLException {
        List<Etudiant> etudiants = new ArrayList<>();
        String req = "SELECT * FROM etudiant;";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(req)) {
            while (rs.next()) {
                etudiants.add(new Etudiant(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getInt("age"),
                        rs.getString("nomUtilisateur"),
                        rs.getString("motDePasse"),
                        rs.getInt("idFormation"),
                        rs.getInt("idUtilisateur")
                ));
            }
        }
        return etudiants;
    }
}
