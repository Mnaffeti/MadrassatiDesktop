package com.example.madrassatidesktop.Service;

import com.example.madrassatidesktop.Entite.Utilisateur;
import  com.example.madrassatidesktop.Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurService implements IService<Utilisateur> {


    private Statement ste;

    private static UtilisateurService ser;

    private UtilisateurService() {
        try {
            Connection con1 = DataSource.getInstance().getCon();
            ste = con1.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static UtilisateurService getInstance() {
        if (ser == null) ser = new UtilisateurService();
        return ser;
    }

    @Override
    public void add(Utilisateur utilisateur) throws SQLException {
        String req = "INSERT INTO `utilisateur` (`id`,`nom`, `email`, `motdepassa`) VALUES (NULL+ '" + utilisateur.getnomUtilisateur() + "', '" + utilisateur.getemail() + "', '" + utilisateur.getmotDePasse() + "');";
        ste.executeUpdate(req);
    }

    @Override
    public boolean delete(Utilisateur utilisateur) throws SQLException {
        String req = "DELETE FROM `utilisateur` WHERE id='" + utilisateur.getId() + "';";
        int rowsDeleted = ste.executeUpdate(req);
        return rowsDeleted > 0;
    }

    @Override
    public boolean update(Utilisateur utilisateur) throws SQLException {
        String req = "UPDATE `utilisateur` SET `id`='" + utilisateur.getId() + "', `nom`='" + utilisateur.getnomUtilisateur() + "', `email`='" + utilisateur.getemail() + "', `motdepasse`='" + utilisateur.getmotDePasse() + "' WHERE id='" + utilisateur.getId() + "';";
        int rowsUpdated = ste.executeUpdate(req);
        return rowsUpdated > 0;
    }

    @Override
    public Utilisateur findById(Utilisateur utilisateur) throws SQLException {
        String req = "SELECT * FROM `utilisateur` WHERE id='" + utilisateur.getId() + "';";
        ResultSet res = ste.executeQuery(req);
        if (res.next()) {
            int id = res.getInt(1);
            String nomUtilisateur = res.getString("nomUtilisateur");
            String email = res.getString("email");
            String motDePasse = res.getString("motDePasse");
            return new Utilisateur(id, nomUtilisateur, email, motDePasse);
        }
        return null;
    }


    @Override
    public List<Utilisateur> findAll() throws SQLException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String req = "SELECT * FROM `utilisateur`;";
        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            int id = res.getInt(1);
            String nomUtilisateur = res.getString("nomUtilisateur");
            String email = res.getString("email");
            String motDePasse = res.getString("motDePasse");
            Utilisateur utilisateur = new Utilisateur(id, nomUtilisateur, email, motDePasse);
            utilisateurs.add(utilisateur);
        }
        return utilisateurs;
    }

    public boolean authenticate(String email, String password) {
        String req = "SELECT * FROM `utilisateur` WHERE email = ? AND motdepasse = ?"; // Ensure the column names match your database schema
        try (Connection con = DataSource.getInstance().getCon(); // Use try-with-resources to ensure proper resource management
             PreparedStatement pstmt = con.prepareStatement(req)) {

            // Set parameters
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            // Check if a record was found
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}