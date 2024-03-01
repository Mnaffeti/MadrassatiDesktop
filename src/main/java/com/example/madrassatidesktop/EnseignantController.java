package com.example.madrassatidesktop;

import com.example.madrassatidesktop.Entite.Enseignant;
import com.example.madrassatidesktop.Service.EnseignantService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.util.Optional;

public class EnseignantController {

    @FXML
    private TableView<Enseignant> teacherTable;

    private EnseignantService enseignantService;

    public EnseignantController() {
        this.enseignantService = EnseignantService.getInstance();
    }

    @FXML
    void initialize() {
        // Initialize teacher table
        initTeacherTable();
        // Load data into teacher table
        loadTeacherData();
    }

    private void initTeacherTable() {
        TableColumn<Enseignant, String> nomColumn = new TableColumn<>("Nom");
        nomColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNom()));

        TableColumn<Enseignant, String> prenomColumn = new TableColumn<>("Prenom");
        prenomColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrenom()));

        TableColumn<Enseignant, String> specialiteColumn = new TableColumn<>("Specialite");
        specialiteColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSpecialite()));

        teacherTable.getColumns().addAll(nomColumn, prenomColumn, specialiteColumn);
    }


    private void loadTeacherData() {
        try {
            ObservableList<Enseignant> teachers = FXCollections.observableArrayList(enseignantService.findAll());
            teacherTable.setItems(teachers);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
    }

    @FXML
    void addTeacher() {
        // Implement add teacher functionality
        // You can open a new window or dialog for adding a teacher
    }

    @FXML
    void deleteTeacher() {
        Enseignant selectedTeacher = teacherTable.getSelectionModel().getSelectedItem();
        if (selectedTeacher != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Are you sure you want to delete this teacher?");
            alert.setContentText("This action cannot be undone.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    boolean deleted = enseignantService.delete(selectedTeacher);
                    if (deleted) {
                        teacherTable.getItems().remove(selectedTeacher);
                    } else {
                        // Handle deletion failure
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle exception
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No teacher selected");
            alert.setContentText("Please select a teacher to delete.");
            alert.showAndWait();
        }
    }

    @FXML
    void updateTeacher() {
    }
}
