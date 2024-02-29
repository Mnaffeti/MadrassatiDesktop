package com.example.madrassatidesktop;


import com.example.madrassatidesktop.Entite.Enseignant;
import com.example.madrassatidesktop.Service.EnseignantService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TeacherController {

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField prenomTextField;

    @FXML
    private TextField specialiteTextField;

    @FXML
    private TableView<Enseignant> teacherTable;

    private EnseignantService enseignantService;

    public TeacherController() {
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
        // Define table columns
        TableColumn<Enseignant, String> nomColumn = new TableColumn<>("Nom");
        nomColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());

        TableColumn<Enseignant, String> prenomColumn = new TableColumn<>("Prenom");
        prenomColumn.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());

        TableColumn<Enseignant, String> specialiteColumn = new TableColumn<>("Specialite");
        specialiteColumn.setCellValueFactory(cellData -> cellData.getValue().specialiteProperty());

        teacherTable.getColumns().addAll(nomColumn, prenomColumn, specialiteColumn);
    }

    private void loadTeacherData() {
        try {
            List<Enseignant> teachers = enseignantService.findAll();
            teacherTable.getItems().addAll(teachers);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
    }

    @FXML
    void addTeacher() {
        String nom = nomTextField.getText();
        String prenom = prenomTextField.getText();
        String specialite = specialiteTextField.getText();
        // Generate an ID (you may have your own logic)
        int idUtilisateur = generateId();
        Enseignant newTeacher = new Enseignant(nom, prenom, specialite, idUtilisateur);
        try {
            enseignantService.add(newTeacher);
            teacherTable.getItems().add(newTeacher);
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
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
        Enseignant selectedTeacher = teacherTable.getSelectionModel().getSelectedItem();
        if (selectedTeacher != null) {
            // Get updated data from text fields
            String nom = nomTextField.getText();
            String prenom = prenomTextField.getText();
            String specialite = specialiteTextField.getText();
            // Update selected teacher
            selectedTeacher.setNom(nom);
            selectedTeacher.setPrenom(prenom);
            selectedTeacher.setSpecialite(specialite);
            try {
                boolean updated = enseignantService.update(selectedTeacher);
                if (updated) {
                    teacherTable.refresh(); // Refresh table view
                    clearFields();
                } else {
                    // Handle update failure
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exception
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No teacher selected");
            alert.setContentText("Please select a teacher to update.");
            alert.showAndWait();
        }
    }

    private int generateId() {
        // You can implement your own logic for generating IDs
        return (int) (Math.random() * 1000);
    }

    private void clearFields() {
        nomTextField.clear();
        prenomTextField.clear();
        specialiteTextField.clear();
    }
}
