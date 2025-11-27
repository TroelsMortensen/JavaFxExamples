import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RunApp extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Scene scene = new Scene(new AnchorPane(), 400, 100);
        primaryStage.setScene(scene);
        ViewManager.setScene(scene);
        ViewManager.showView("InputView");
        primaryStage.show();
    }
}
