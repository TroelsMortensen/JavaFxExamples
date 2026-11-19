package extraterrestrialexploration.presentation.controllers.alienmanagement;

import extraterrestrialexploration.domain.Alien;
import extraterrestrialexploration.persistence.DataManager;
import extraterrestrialexploration.presentation.core.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddAlienController
{
    @FXML
    private TextField nameField;

    @FXML
    private TextField speciesField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private Label messageLabel;

    private final DataManager dataManager;

    public AddAlienController(DataManager dataManager)
    {
        this.dataManager = dataManager;
    }

    @FXML
    private void handleSave()
    {
        String name = nameField.getText().trim();
        String species = speciesField.getText().trim();
        String description = descriptionField.getText().trim();

        if (name.isEmpty() || species.isEmpty() || description.isEmpty())
        {
            showError("Please fill in all required fields");
            return;
        }

        dataManager.addAlien(new Alien(name, species, description));
        messageLabel.setText("Alien added successfully!");
        messageLabel.getStyleClass().setAll("message-success");

        nameField.clear();
        speciesField.clear();
        descriptionField.clear();
    }

    @FXML
    private void handleCancel()
    {
        ViewManager.showView("alienmanagement/ListAliens");
    }

    private void showError(String message)
    {
        messageLabel.setText(message);
        messageLabel.getStyleClass().setAll("message-error");
    }
}
