package com.example.madrassatidesktop.Service;

import com.example.madrassatidesktop.Entite.Enseignant;
import com.example.madrassatidesktop.Utils.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EnseignantService implements IService<Enseignant> {

    private Statement ste;
    private static EnseignantService ser;

    private EnseignantService() {
        try {
            Connection con1 = DataSource.getInstance().getCon();
            ste = con1.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static EnseignantService getInstance() {
        if (ser == null) ser = new EnseignantService();
        return ser;
    }

    @Override
    public void add(Enseignant enseignant) throws SQLException {
        String req = "INSERT INTO enseignant (nom, prenom, specialite, idUtilisateur) VALUES ('"
                + enseignant.getNom() + "', '"
                + enseignant.getPrenom() + "', '"
                + enseignant.getSpecialite() + "', "
                + enseignant.getIdUtilisateur() + ");";
        ste.executeUpdate(req);
    }

    @Override
    public boolean delete(Enseignant enseignant) throws SQLException {
        String req = "DELETE FROM enseignant WHERE idEnseignant = " + enseignant.getIdUtilisateur() + ";";
        int rowsDeleted = ste.executeUpdate(req);
        return rowsDeleted > 0;
    }

    @Override
    public boolean update(Enseignant enseignant) throws SQLException {
        String req = "UPDATE enseignant SET nom = '" + enseignant.getNom()
                + "', prenom = '" + enseignant.getPrenom()
                + "', specialite = '" + enseignant.getSpecialite()
                + "', idUtilisateur = " + enseignant.getIdUtilisateur()
                + " WHERE idEnseignant = " + enseignant.getIdUtilisateur() + ";";
        int rowsUpdated = ste.executeUpdate(req);
        return rowsUpdated > 0;
    }

    @Override
    public Enseignant findById(Enseignant enseignant) throws SQLException {
        return null;
    }

    @Override
    public Enseignant findById(int t) throws SQLException {
        return null;
    }

    @Override
    public List<Enseignant> findAll() throws SQLException {
        List<Enseignant> enseignants = new ArrayList<>();
        String req = "SELECT * FROM enseignant;";
        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            Enseignant enseignant = new Enseignant(
                    res.getString("nom"),
                    res.getString("prenom"),
                    res.getString("specialite"),
                    res.getInt("idUtilisateur")
            );
            enseignants.add(enseignant);
        }
        return enseignants;
    }
}
