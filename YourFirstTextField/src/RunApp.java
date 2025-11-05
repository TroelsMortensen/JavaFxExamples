import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RunApp extends Application
{
    @Override public void start(Stage primaryStage) throws Exception
    {
        TextField textField = new TextField();
        Label output = new Label();
        Button button = new Button("Click me");
        button.setOnAction(_ -> {
            output.setText(textField.getText());
            textField.setText("");
        });

        VBox box = new VBox(textField, button, output);
        Scene scene = new Scene(box);
        primaryStage.setScene(scene);
        primaryStage.setHeight(400);
        primaryStage.setWidth(400);
        primaryStage.show();
    }
}
