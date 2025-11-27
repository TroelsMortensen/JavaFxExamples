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
        Label dataShowingLabel = new Label();

        Button displayDataButton = new Button("Show data");
        displayDataButton.setOnAction(_ -> {
            String result = dataManager.getStrings().stream().collect(Collectors.joining("\n"));
            dataShowingLabel.setText(result);
        });

        VBox box = new VBox(textField, button, displayDataButton, dataShowingLabel);
        Scene scene = new Scene(box);
        stage.setScene(scene);
        stage.setHeight(400);
        stage.setWidth(400);
        stage.show();
    }
}
