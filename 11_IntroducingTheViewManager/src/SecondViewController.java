import javafx.scene.control.Label;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SecondViewController
{
    public Label timeLabel;

    public void onTimeButtonPressed()
    {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = formatter.format(now);
        timeLabel.setText(formattedTime);
    }

    public void navigateToFirstView()
    {
        ViewManager.showView("FirstView");
    }
}
