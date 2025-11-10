import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MyExampleController implements Initializable
{
    public TextField inputField;
    public Button transferButton;
    public Label outputLabel;



    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        transferButton.setDisable(true);
    }

    public void onInputFieldKeyReleased()
    {
        String text = inputField.getText();
        boolean shouldDisable = text == null || text.isEmpty();
        transferButton.setDisable(shouldDisable);
    }

    public void onTransferButtonPressed()
    {
        String text = inputField.getText();
        outputLabel.setText(text);
        inputField.setText("");
        transferButton.setDisable(true);
    }

    public void onClearButtonPressed()
    {
        outputLabel.setText("");
        inputField.setText("");
        transferButton.setDisable(true);
    }
}
