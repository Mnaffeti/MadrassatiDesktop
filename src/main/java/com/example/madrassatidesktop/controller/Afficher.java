package com.example.madrassatidesktop.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.madrassatidesktop.entity.Module;
import com.example.madrassatidesktop.service.ModuleService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class Afficher {
    public Label title;
    ModuleService ss=new ModuleService();
    @FXML
    private Button btnadd1;
    @FXML
    private Button dashb;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnmodif;

    @FXML
    private Button btnsupp;

    @FXML
    private TableColumn<Module, String> cldesc;

    @FXML
    private TableColumn<Module, String> clnom;

    @FXML
    private TextField searchField;


    @FXML
    private TextField tfdesc1;

    @FXML
    private TextField tfnom1;

    @FXML
    private TableView<Module> tvmodule;
    private Module module;


    @FXML
    void initialize() {
        try {
            ObservableList<Module> observableList = FXCollections.observableList(ss.readAll());
            tvmodule.setItems(observableList);
            clnom.setCellValueFactory(new PropertyValueFactory<>("nom_module"));
            cldesc.setCellValueFactory(new PropertyValueFactory<>("description"));

        }catch (Exception e){
        throw new RuntimeException(e);
        }

    }

    public void mouseClicked1(MouseEvent mouseEvent) {
        try{
            Module s=tvmodule.getSelectionModel().getSelectedItem();
            System.out.println(s);
            if(s !=null) {

                module = new Module(s.getId_module(), s.getNom_module(),  s.getDescription());
                tfnom1.setText(module.getNom_module());
                tfdesc1.setText(String.valueOf(module.getDescription()));

            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    public void delete(ActionEvent actionEvent) {
        try {
            ModuleService ss = new ModuleService();
            if (module != null) {
                ss.delete(module.getId_module());
                initialize();
                showAlert("Information", "module supprimée", Alert.AlertType.INFORMATION);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);;
        }
    }

    public void update(ActionEvent actionEvent) {
        try {
            if (module != null) {
                ModuleService ss = new ModuleService();
                // Mettre à jour les informations de la module
                module.setNom_module(tfnom1.getText());
                module.setDescription(tfdesc1.getText());
                ss.updato(module);
                initialize(); // Rafraîchir la TableView
                showAlert("Information", "module modifiée", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Error", "Veuillez sélectionner une module à modifier", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    public void add(ActionEvent actionEvent) {
        try {
            String nom = tfnom1.getText().trim();
            String desc = tfdesc1.getText().trim();

            // Validation de la saisie
            if (nom.isEmpty() || desc.isEmpty()) {
                showAlert("Error", "Veuillez remplir tous les champs.", Alert.AlertType.ERROR);
                return;
            }

            if (nom.length() < 3 || nom.length() > 50) {
                showAlert("Error", "Le nom doit contenir entre 3 et 50 caractères.", Alert.AlertType.ERROR);
                return;
            }

            if (!nom.matches("^[A-Z][a-zA-Z]*$")) {
                showAlert("Error", "Le nom doit commencer par une majuscule et ne doit contenir que des lettres.", Alert.AlertType.ERROR);
                return;
            }

            if (desc.length() < 3 || desc.length() > 200) {
                showAlert("Error", "La description doit contenir entre 10 et 200 caractères.", Alert.AlertType.ERROR);
                return;
            }
            ss.add(new Module(nom, desc));
            initialize();
            showAlert("Information", "Module ajoutée", Alert.AlertType.INFORMATION);
        } catch(Exception e){
            showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void recherche(ActionEvent actionEvent) {
        try {
            // Récupérer toutes les modules
            ObservableList<Module> observableList = FXCollections.observableList(ss.readAll());

            // Créer un FilteredList lié à la liste observable
            FilteredList<Module> filteredList = new FilteredList<>(observableList, p -> true);

            // Ajouter un écouteur sur le champ de recherche
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(module -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true; // Afficher toutes les modules quand le champ de recherche est vide
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    // Vérifier si le nom de la module ou la description contient le texte de recherche
                    return module.getNom_module().toLowerCase().contains(lowerCaseFilter)
                            || module.getDescription().toLowerCase().contains(lowerCaseFilter);
                });
            });

            // Créer une SortedList liée au FilteredList et triée selon le comparateur de la table
            SortedList<Module> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(tvmodule.comparatorProperty());

            // Mettre à jour les données de la table avec la liste triée et filtrée
            tvmodule.setItems(sortedList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }





    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void dash2(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Statistique.fxml"));
            dashb.getScene().setRoot(root);
        } catch (Exception e) {
            showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

}
