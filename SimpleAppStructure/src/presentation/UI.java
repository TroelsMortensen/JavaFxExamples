package presentation;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import persistence.DataManager;

import java.util.stream.Collectors;

public class UI
{
    private final DataManager dataManager;

    public UI(DataManager dataManager)
    {
        this.dataManager = dataManager;
    }

    public void show(Stage stage)
    {
        TextField textField = new TextField();
        Button button = new Button("Add string");
        button.setOnAction(_ -> {
            dataManager.addString(textField.getText());
            textField.setText("");
        });

        Label output = new Label();
        Button showData = new Button("Show data");
        showData.setOnAction(_ ->{
            String result = dataManager.getStrings().stream().collect(Collectors.joining("\n"));
            output.setText(result);
        });

        VBox box = new VBox(textField, button, showData, output);
        Scene scene = new Scene(box);
        stage.setScene(scene);
        stage.setWidth(400);
        stage.setHeight(400);
        stage.show();
    }
}
