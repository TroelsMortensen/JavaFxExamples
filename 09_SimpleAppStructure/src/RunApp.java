import javafx.application.Application;
import javafx.stage.Stage;
import persistence.DataManager;
import presentation.UI;

public class RunApp extends Application
{
    @Override public void start(Stage primaryStage) throws Exception
    {
        DataManager dm = new DataManager();
        UI ui = new UI(dm);
        ui.show(primaryStage);
    }
}
