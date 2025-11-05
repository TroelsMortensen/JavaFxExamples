import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RunApp extends Application
{
    @Override public void start(Stage primaryStage) throws Exception
    {
        Button button = new Button("Click me");
        button.setOnAction(RunApp::handleClick);
        VBox box = new VBox(button);
        Scene scene = new Scene(box);
        primaryStage.setScene(scene);
        primaryStage.setHeight(400);
        primaryStage.setWidth(400);
        primaryStage.show();
    }

    private static void handleClick(ActionEvent evt)
    {
        System.out.println("Button was clicked");
    }
}
