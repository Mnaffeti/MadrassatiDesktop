package com.example.madrassatidesktop;

import com.calendarfx.model.Entry;
import com.example.madrassatidesktop.Entite.Cours;
import com.example.madrassatidesktop.Entite.CourseMark;
import com.example.madrassatidesktop.Entite.Exam;
import com.example.madrassatidesktop.HelloApplication;
import com.example.madrassatidesktop.Service.CoursService;
import com.example.madrassatidesktop.Service.ExamService;
import com.example.madrassatidesktop.Service.UtilisateurService;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
    private Label NbOfUpcoming;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Label StudentName;
    @FXML
    private Label StudentNameAr;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label NbExams;
    @FXML
    private Label avgMarks;
    @FXML
    private Label Studentformation;
    @FXML
    private  Pane StatPane;
    private  int id;
    private ExamService examService; // Assume this is initialized elsewhere

    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void setStudentinfos(String name,int nbexam,float avgmarks, int nbcours,String formation) {
        StudentName.setText(name);
        NbExams.setText(String.valueOf(nbexam));
        avgMarks.setText(String.valueOf(avgmarks));
        NbOfUpcoming.setText(String.valueOf(nbcours));
        Studentformation.setText(formation);


    }
    public void displayCourseMarksChart(List<CourseMark> courseMarks) {
        // Create the axes
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Course");
        yAxis.setLabel("Marks");

        // Create the BarChart
        final BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Course Marks");

        // Create a data series
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Marks by Course");

        // Populate the series with data from courseMarks
        for (CourseMark cm : courseMarks) {
            series.getData().add(new XYChart.Data<>(cm.getLibeleCours(), cm.getMarks()));
        }

        // Add the series to the chart
        barChart.getData().add(series);

        // Add the chart to the pane
        StatPane.getChildren().clear(); // Clear existing content if any
        StatPane.getChildren().add(barChart); // Add the chart
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

    public void ShowStats(ActionEvent event) throws SQLException {
        List<CourseMark> tmp =ExamService.getInstance().getCourseMarksByUserId(this.id);
        displayCourseMarksChart(tmp);
    }
    public  void setId(int id){this.id=id ;}

    public void TakeMeToLogin(ActionEvent event) {
        try {
            // Load the Main Window view
            Parent mainWindowParent = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
            Scene mainWindowScene = new Scene(mainWindowParent);


            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();


            window.setScene(mainWindowScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
