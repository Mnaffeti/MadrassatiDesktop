package com.example.madrassatidesktop.Service;

import com.example.madrassatidesktop.Entite.Etudiant;
import com.example.madrassatidesktop.Entite.Utilisateur;
import  com.example.madrassatidesktop.Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurService implements IService<Utilisateur> {


    private Statement ste;
    private Connection con;
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
        String req = "INSERT INTO utilisateur ( email, motdepasse) VALUES ( '"  + utilisateur.getemail() + "', '" + utilisateur.getmotDePasse() + "');";
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
        String req = "UPDATE `utilisateur` SET `idUtilisateur`='" + utilisateur.getId() + "', `email`='" + utilisateur.getemail() + "', `motdepasse`='" + utilisateur.getmotDePasse()  + "' WHERE id='" + utilisateur.getId() + "';";
        int rowsUpdated = ste.executeUpdate(req);
        return rowsUpdated > 0;
    }

    @Override
    public Utilisateur findById(Utilisateur utilisateur) throws SQLException {
        return null;
    }

    @Override
    public Utilisateur findById(int t) throws SQLException {
        return null;
    }

    public  int getUserIdByEmail(String email) {
        System.out.println(email);
        // SQL query to find the user ID
        String sql = "SELECT idUtilisateur as 'idUtilisateur' FROM utilisateur WHERE email ='"+email+"' ;" ;

        // Local variable to store the ID. Initialized to -1 or another invalid value to represent not found.
        int userId = -1;

        try {
            // Establishing the connection


            // Preparing the statement to prevent SQL injection
            UtilisateurService ser =UtilisateurService.getInstance() ;

            // Executing the query

            ResultSet rs = ste.executeQuery(sql);

            // Retrieving the ID from the ResultSet
            if (rs.next()) {
                userId = rs.getInt("idUtilisateur");
            }

            // Closing resources


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId; // Returning the user ID (or -1 if not found)
    }
    @Override
    public List<Utilisateur> findAll() throws SQLException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String req = "SELECT * FROM `utilisateur`;";
        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            int id = res.getInt(1);
            String email = res.getString("email");
            String motDePasse = res.getString("motDePasse");
            Utilisateur utilisateur = new Utilisateur(email, motDePasse);
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