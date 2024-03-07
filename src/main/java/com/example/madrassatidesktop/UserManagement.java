package com.example.madrassatidesktop;

import com.example.madrassatidesktop.Entite.Etudiant;
import com.example.madrassatidesktop.Service.EtudiantService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class UserManagement implements Initializable {

    @FXML
    private Button DeleteBtn;

    @FXML
    private TableColumn<?, ?> FormationStudent;

    @FXML
    private TableColumn<?, ?>AgeStudent;

    @FXML
    private TableColumn<?, ?> NameStudent;

    @FXML
    private TextField StudentFormationField;

    @FXML
    private TextField StudentMobileField;

    @FXML
    private TextField StudentNameField;

    @FXML
    private TextField StudentSurnameField;

    @FXML
    private TableView<Etudiant> StudentTable;

    @FXML
    private TableColumn<?, ?> SurnameStudent;

    @FXML
    private Button UpdateBtn;

    @FXML
    private TableColumn<?, ?> idStudent;

    @FXML
    void DeleteBtnAction(ActionEvent event) {

    }

    @FXML
    void UpdateBtnAction(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadStudents();
    }
    private void loadStudents() {
        EtudiantService service = EtudiantService.getInstance();
        try {
            List<Etudiant> etudiantList = service.findAll();
            ObservableList<Etudiant> etudiantObservableList = FXCollections.observableArrayList(etudiantList); // Convert to ObservableList
            StudentTable.setItems(etudiantObservableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

