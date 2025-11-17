import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;

public class RadioButtonExample extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ToggleGroup group = new ToggleGroup();

        RadioButton option1 = new RadioButton("Small");
        option1.setOnAction(_ -> System.out.println("Click here"));
        RadioButton option2 = new RadioButton("Medium");
        RadioButton option3 = new RadioButton("Large");

        option1.setToggleGroup(group);
        option2.setToggleGroup(group);
        option3.setToggleGroup(group);
        VBox pane = new VBox(option1, option2, option3);
        primaryStage.setScene(new Scene(pane, 200,100));
        primaryStage.show();
    }
}
