package extraterrestrialexploration.presentation.controllers.alienmanagement;

import extraterrestrialexploration.domain.Alien;
import extraterrestrialexploration.persistence.DataManager;
import extraterrestrialexploration.presentation.core.AcceptsStringArgument;
import extraterrestrialexploration.presentation.core.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class UpdateAlienController implements AcceptsStringArgument
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
    private int alienId;

    public UpdateAlienController(DataManager dataManager)
    {
        this.dataManager = dataManager;
    }

    @Override
    public void setArgument(String argument)
    {
        alienId = Integer.parseInt(argument);
        Alien alien = dataManager.getAlien(alienId);
        if (alien == null)
        {
            showError("Alien not found");
            return;
        }

        nameField.setText(alien.getName());
        speciesField.setText(alien.getSpecies());
        descriptionField.setText(alien.getPhysicalDescription());
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

        Alien alien = new Alien(name, species, description);
        alien.setId(alienId);
        dataManager.updateAlien(alien);

        messageLabel.setText("Alien updated successfully!");
        messageLabel.getStyleClass().setAll("message-success");
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
