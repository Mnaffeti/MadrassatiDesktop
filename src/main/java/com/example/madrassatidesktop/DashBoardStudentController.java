package com.example.madrassatidesktop;

import com.calendarfx.model.Entry;
import com.example.madrassatidesktop.Entite.Cours;
import com.example.madrassatidesktop.HelloApplication;
import com.example.madrassatidesktop.Service.CoursService;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.Scene;
import com.calendarfx.view.CalendarView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import javafx.util.Duration;

public class DashBoardStudentController implements Initializable {
    @FXML
    private Button OpenCalendar;

    @FXML
    private Button ShowStatBtn;

    @FXML
    private Label StudentNameLabel;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnSignout;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlMenus;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;
    @FXML
    private Label StudentName;
    @FXML
    private Label StudentNameAr;
    @FXML
    private AnchorPane rootPane;
    @FXML
    
    public void initialize(URL url, ResourceBundle resourceBundle) {




    }


    @FXML
    void OpenCalendarAction(ActionEvent event) throws SQLException {
        CalendarView calendarView = new CalendarView();

        Calendar cours = new Calendar("Cours");
        CoursService cs = CoursService.getInstance();
        List<Cours> courses =cs.findAll();

        // Convert each Course object into a CalendarFX entry and add it to the calendar
        for (Cours course : courses) {
            Entry<String> entry = new Entry<>(course.getLibeleCours());
            entry.setInterval(course.getDateDebut(), course.getDateFin());
            cours.addEntry(entry);
        }
        Calendar holidays = new Calendar("Holidays");
        cours.setStyle(Calendar.Style.STYLE4);
        holidays.setStyle(Calendar.Style.STYLE2);

        CalendarSource myCalendarSource = new CalendarSource("Welcome");
        myCalendarSource.getCalendars().addAll(cours, holidays);

        calendarView.getCalendarSources().addAll(myCalendarSource);
        calendarView.setRequestedTime(LocalTime.now());

        Stage calendarStage = new Stage();
        calendarStage.setTitle("Student calendar");
        Scene scene = new Scene(calendarView);
        calendarStage.setScene(scene);
        calendarStage.setWidth(1300);
        calendarStage.setHeight(800);
        calendarStage.centerOnScreen();
        calendarStage.show();

    }

    public void handleClicks(ActionEvent event) {
    }

    public void ShowStats(ActionEvent event) {
    }

    public void TakeMeToLogin(ActionEvent event) {
    }
}
