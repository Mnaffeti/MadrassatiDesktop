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
    private Label StudentName;
    @FXML
    private Label StudentNameAr;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button OpenCalendar;
    public void initialize(URL url, ResourceBundle resourceBundle) {

           // loadWelcomeScreen();


    }

    /*private void loadWelcomeScreen() {
        try {
            //Load splash screen view FXML
            AnchorPane pane = FXMLLoader.load(getClass().getResource(("WelcomeSplash.fxml")));
            //Add it to root container (Can be StackPane, AnchorPane etc)
            rootPane.getChildren().setAll(pane);

            //Load splash screen with fade in effect
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            //Finish splash with fade out effect
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(5), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            //After fade in, start fade out
            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            //After fade out, load actual content
            fadeOut.setOnFinished((e) -> {
                try {
                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("DashBoardStudent.fxml")));
                    rootPane.getChildren().setAll(parentContent);
                } catch (IOException ex) {

                }
            });
        } catch (IOException ex) {

        }

    }*/

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

}
