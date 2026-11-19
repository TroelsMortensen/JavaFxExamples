package extraterrestrialexploration.presentation.controllers.planetmanagement;

import extraterrestrialexploration.domain.Planet;
import extraterrestrialexploration.persistence.DataManager;
import extraterrestrialexploration.presentation.core.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddPlanetController
{
    @FXML
    private TextField nameField;

    @FXML
    private TextArea climateField;

    @FXML
    private TextField distanceField;

    @FXML
    private CheckBox atmosphereCheckBox;

    @FXML
    private CheckBox lifeCheckBox;

    @FXML
    private Label messageLabel;

    private final DataManager dataManager;

    public AddPlanetController(DataManager dataManager)
    {
        this.dataManager = dataManager;
    }

    @FXML
    private void handleSave()
    {
        String name = nameField.getText().trim();
        String climate = climateField.getText().trim();
        String distanceText = distanceField.getText().trim();

        if (name.isEmpty() || climate.isEmpty() || distanceText.isEmpty())
        {
            showError("Please fill in all required fields");
            return;
        }

        double distance;
        try
        {
            distance = Double.parseDouble(distanceText);
        }
        catch (NumberFormatException e)
        {
            showError("Distance must be a valid number");
            return;
        }

        Planet planet = new Planet(name, climate, distance, atmosphereCheckBox.isSelected(), lifeCheckBox.isSelected());
        dataManager.addPlanet(planet);

        messageLabel.setText("Planet added successfully!");
        messageLabel.getStyleClass().setAll("message-success");

        nameField.clear();
        climateField.clear();
        distanceField.clear();
        atmosphereCheckBox.setSelected(false);
        lifeCheckBox.setSelected(false);
    }

    @FXML
    private void handleCancel()
    {
        ViewManager.showView("planetmanagement/ListPlanets");
    }

    private void showError(String message)
    {
        messageLabel.setText(message);
        messageLabel.getStyleClass().setAll("message-error");
    }
}
