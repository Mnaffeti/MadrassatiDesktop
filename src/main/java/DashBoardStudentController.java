import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import com.calendarfx.view.CalendarView;
import java.time.LocalDate;
import java.time.LocalTime;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
public class DashBoardStudentController {

    @FXML
    private Button OpenCalendar;

    @FXML
    void OpenCalendarAction(ActionEvent event) {
        CalendarView calendarView = new CalendarView();

        Calendar birthdays = new Calendar("Birthdays");
        Calendar holidays = new Calendar("Holidays");
        birthdays.setStyle(Calendar.Style.STYLE1);
        holidays.setStyle(Calendar.Style.STYLE2);

        CalendarSource myCalendarSource = new CalendarSource("My Calendars");
        myCalendarSource.getCalendars().addAll(birthdays, holidays);

        calendarView.getCalendarSources().addAll(myCalendarSource);
        calendarView.setRequestedTime(LocalTime.now());

        Stage calendarStage = new Stage();
        calendarStage.setTitle("Calendar");
        Scene scene = new Scene(calendarView);
        calendarStage.setScene(scene);
        calendarStage.setWidth(1300);
        calendarStage.setHeight(1000);
        calendarStage.centerOnScreen();
        calendarStage.show();

    }

}
