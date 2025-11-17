import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RunApp extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ViewManager.init(primaryStage, "InputView");
    }
}
