package com.example.madrassatidesktop.controller;

import com.example.madrassatidesktop.entity.Cour;
import com.example.madrassatidesktop.service.CourService;
import com.example.madrassatidesktop.service.ModuleService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;

import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.stream.Collectors;

public class Statistique {

    @FXML
    private PieChart pi_chart;
    @FXML
    private Button btnCour;

    @FXML
    private Label lblTotalCoursValue;
    @FXML
    private Label lblTotalModulesValue;
    private CourService courService = new CourService();// Assurez-vous d'initialiser le service approprié pour gérer les cours

    private ModuleService moduleService = new ModuleService(); // Ajout du service pour gérer les modules

    @FXML
    private AreaChart<String, Number> area_chart;

    @FXML
    void initialize() {
        try {
            assert btnCour != null : "fx:id=\"btnCour\" was not injected: check your FXML file 'Statistique.fxml'.";
            statArea();
            afficherNombreTotalCours();
            afficherNombreTotalModules(); // Appel de la méthode pour afficher le total des modules

        } catch (Exception e) {
            showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


    public void statArea() {
        ObservableList<Cour> courList = FXCollections.observableList(courService.readAll());

        // Mettre en œuvre la logique pour collecter les données nécessaires pour votre AreaChart


        Map<String, Long> moduleCounts = courList.stream().collect(Collectors.groupingBy(Cour::getNom_module, Collectors.counting()));

        // Utilisez les données collectées pour créer des séries pour votre AreaChart
        // Exemple:
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        moduleCounts.forEach((module, count) -> {
            series.getData().add(new XYChart.Data<>(module, count));
        });

        // Ajouter la série à votre AreaChart
        area_chart.getData().add(series);
    }

    public void afficherNombreTotalCours() {
        int totalCours = courService.getNombreTotalCours(); // Méthode à implémenter dans votre service CoursService
        lblTotalCoursValue.setText(Integer.toString(totalCours));
    }

    public void afficherNombreTotalModules() {
        int totalModules = moduleService.getNombreTotalModules(); // Utilisation du service pour récupérer le total des modules
        this.lblTotalModulesValue.setText(Integer.toString(totalModules)); // Affichage du total des modules dans le Label correspondant
    }

    public void homeC(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/CourAffichage.fxml"));
            btnCour.getScene().setRoot(root);
        } catch (Exception e) {
            showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    public void homeD(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Afficher.fxml"));
            btnCour.getScene().setRoot(root);
        } catch (Exception e) {
            showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
