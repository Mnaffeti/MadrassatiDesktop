package com.example.madrassatidesktop.entity;

import java.util.Objects;

public class Module {
    private int id_module;
    private String nom_module;
    private String description;

    public Module(String nom_module, String description) {
        this.nom_module = nom_module;
        this.description = description;
    }

    public Module(int id_module, String nom_module, String description) {
        this.id_module = id_module;
        this.nom_module = nom_module;
        this.description = description;
    }

    public int getId_module() {
        return id_module;
    }

    public void setId_module(int id_module) {
        this.id_module = id_module;
    }

    public String getNom_module() {
        return nom_module;
    }

    public void setNom_module(String nom_module) {
        this.nom_module = nom_module;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Module module = (Module) o;
        return id_module == module.id_module && Objects.equals(nom_module, module.nom_module) && Objects.equals(description, module.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_module, nom_module, description);
    }

    @Override
    public String toString() {
        return "Module{" +
                "id_module=" + id_module +
                ", nom_module='" + nom_module + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
