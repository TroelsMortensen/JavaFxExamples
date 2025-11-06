package presentation;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import persistence.DataManager;

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
    }
}
