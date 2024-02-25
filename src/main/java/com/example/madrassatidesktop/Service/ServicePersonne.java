package com.example.madrassatidesktop.Service;

import com.example.madrassatidesktop.Entite.Personne;
import  com.example.madrassatidesktop.Utils.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServicePersonne implements IService<Personne> {

    private Statement ste;

    private static ServicePersonne ser;

    private ServicePersonne() {
        try {
            Connection con1 = DataSource.getInstance().getCon();
            ste = con1.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ServicePersonne getInstance() {
        if (ser == null) ser = new ServicePersonne();
        return ser;
    }

    @Override
    public void add(Personne personne) throws SQLException {
        String req = "INSERT INTO `personne` (`id`, `nom`, `prenom`, `age`) VALUES (NULL, '" + personne.getNom() + "', '" + personne.getPrenom() + "', '" + personne.getAge() + "');";
        ste.executeUpdate(req);
    }

    @Override
    public boolean delete(Personne personne) throws SQLException {
        String req = "DELETE FROM `personne` WHERE id='" + personne.getId() + "';";
        int rowsDeleted = ste.executeUpdate(req);

        return rowsDeleted > 0;
    }

    @Override
    public boolean update(Personne personne) throws SQLException {
        String req = "UPDATE `personne` SET `nom`='" + personne.getNom() + "', `prenom`='" + personne.getPrenom() + "', `age`='" + personne.getAge() + "' WHERE id='" + personne.getId() + "';";

        int rowsUpdated = ste.executeUpdate(req);

        return rowsUpdated > 0;
    }

    @Override
    public Personne findById(Personne personne) throws SQLException {
        String req = "SELECT * FROM `personne` WHERE id='" + personne.getId() + "';";
        ResultSet res = ste.executeQuery(req);

        if (res.next()) {
            int id = res.getInt(1);
            String nom = res.getString(2);
            String prenom = res.getString("prenom");
            int age = res.getInt("age");

            return new Personne(id, nom, prenom, age);
        }

        return null;
    }


    @Override
    public List<Personne> findAll() throws SQLException {
        List<Personne> l1 = new ArrayList<>();
        ResultSet res = ste.executeQuery("select  * from personne");
        while (res.next()) {
            int id = res.getInt(1);
            String nom = res.getString(2);
            String prenom = res.getString("prenom");
            int age = res.getInt("age");
            System.out.println("id :" + id + "nom :" + nom + "prenom :" + prenom + " age :" + age);
            Personne p = new Personne(id, nom, prenom, age);
            l1.add(p);

        }

        return l1;
    }
}
