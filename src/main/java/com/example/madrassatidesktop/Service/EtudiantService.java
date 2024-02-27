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
        String req = "INSERT INTO etudiant (nom, prenom, age, idFormation, idUtilisateur) VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement pstmt = con.prepareStatement(req)) {
            pstmt.setString(1, etudiant.getNom());
            pstmt.setString(2, etudiant.getPrenom());
            pstmt.setInt(3, etudiant.getAge());
            pstmt.setInt(4, etudiant.getIdFormation());
            pstmt.setInt(5, etudiant.getIdUtilisateur());
            pstmt.executeUpdate();
        }
    }

    @Override
    public boolean delete(Etudiant etudiant) throws SQLException {
        String req = "DELETE FROM etudiant WHERE id = ?;";
        try (PreparedStatement pstmt = con.prepareStatement(req)) {
            pstmt.setInt(1, etudiant.getIdUtilisateur());
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        }
    }

    @Override
    public boolean update(Etudiant etudiant) throws SQLException {
        String req = "UPDATE etudiant SET nom = ?, prenom = ?, age = ?, idFormation = ?, idUtilisateur = ? WHERE id = ?;";
        try (PreparedStatement pstmt = con.prepareStatement(req)) {
            pstmt.setString(1, etudiant.getNom());
            pstmt.setString(2, etudiant.getPrenom());
            pstmt.setInt(3, etudiant.getAge());
            pstmt.setInt(4, etudiant.getIdFormation());
            pstmt.setInt(5, etudiant.getIdUtilisateur());
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
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getInt("age"),
                        rs.getInt("idFormation"),
                        rs.getInt("idUtilisateur")
                ));
            }
        }
        return etudiants;
    }
    public  int getUserIdByEmail(String email) {
        // SQL query to find the user ID
        String sql = "SELECT id FROM utilisateur WHERE mail = ?";

        // Local variable to store the ID. Initialized to -1 or another invalid value to represent not found.
        int userId = -1;

        try {
            // Establishing the connection


            // Preparing the statement to prevent SQL injection
            UtilisateurService ser =UtilisateurService.getInstance() ;

            // Executing the query
            Statement stmt = con.createStatement() ;
            ResultSet rs = stmt.executeQuery(sql);

            // Retrieving the ID from the ResultSet
            if (rs.next()) {
                userId = rs.getInt("id");
            }

            // Closing resources


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId; // Returning the user ID (or -1 if not found)
    }
}
