import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RunApp extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ViewManager.setStage(primaryStage);
        ViewManager.showView("FirstView");
        primaryStage.show();
    }
}
