import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RunApp extends Application
{
    @Override public void start(Stage primaryStage) throws Exception
    {
        Label label = new Label("Hello viewer");
        AnchorPane pane = new AnchorPane();
        pane.getChildren().add(label);
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setHeight(600);
        primaryStage.setWidth(800);
        primaryStage.show();
    }
}
