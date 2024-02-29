package com.example.madrassatidesktop.service;

import com.example.madrassatidesktop.entity.Module;
import com.example.madrassatidesktop.entity.Module;
import com.example.madrassatidesktop.utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModuleService implements IService<Module> {
    private Connection connexion;

    public ModuleService() {
        connexion = DataSource.getInstance().getCon();
    }

    private Statement ste;//yebath lel sql heya interface

    public void add(Module m) {
        String requete = "INSERT INTO module (nom_module, description) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement(requete);
            preparedStatement.setString(1, m.getNom_module());
            preparedStatement.setString(2, m.getDescription());
            preparedStatement.executeUpdate();
            System.out.println("module ajouté ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        String requete = "DELETE FROM module WHERE id_module = ?";
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement(requete);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("module deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Module malle, int id) {

    }

    public void updato(Module m) {
        String requete = "UPDATE module SET nom_module=?, description = ? WHERE id_module = ?";
        try {
            PreparedStatement ps = connexion.prepareStatement(requete);
            ps.setString(1, m.getNom_module());
            ps.setString(2, m.getDescription());
            ps.setInt(3, m.getId_module());
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("module updated!");
            } else {
                System.out.println("No module updated!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Module> readAll() {
        String requete = "SELECT * FROM module";
        List<Module> list = new ArrayList<>();
        try {
            ste = connexion.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {
                list.add(new Module(rs.getInt("id_module"), rs.getString("nom_module"), rs.getString("description")));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Module readById(int id) {
        String requete = "select * from module where id=" + id;
        Module module = null;
        try {
            ste = connexion.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {
                module = new Module(rs.getInt("id_module"), rs.getString("nom_module"), rs.getString("description"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return module;
    }

    public int getNombreTotalModules() {
        String requete = "SELECT COUNT(*) FROM module";
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultSet = statement.executeQuery(requete);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }


}
