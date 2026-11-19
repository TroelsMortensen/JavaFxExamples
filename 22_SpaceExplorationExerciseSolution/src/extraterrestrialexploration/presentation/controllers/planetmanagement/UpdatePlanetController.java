package extraterrestrialexploration.presentation.controllers.planetmanagement;

import extraterrestrialexploration.domain.Planet;
import extraterrestrialexploration.persistence.DataManager;
import extraterrestrialexploration.presentation.core.AcceptsStringArgument;
import extraterrestrialexploration.presentation.core.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class UpdatePlanetController implements AcceptsStringArgument
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
    private int planetId;

    public UpdatePlanetController(DataManager dataManager)
    {
        this.dataManager = dataManager;
    }

    @Override
    public void setArgument(String argument)
    {
        planetId = Integer.parseInt(argument);
        Planet planet = dataManager.getPlanet(planetId);
        if (planet == null)
        {
            showError("Planet not found");
            return;
        }

        nameField.setText(planet.getName());
        climateField.setText(planet.getClimateDescription());
        distanceField.setText(String.valueOf(planet.getDistanceFromStarAU()));
        atmosphereCheckBox.setSelected(planet.hasAtmosphere());
        lifeCheckBox.setSelected(planet.hasLife());
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
        planet.setId(planetId);
        dataManager.updatePlanet(planet);

        messageLabel.setText("Planet updated successfully!");
        messageLabel.getStyleClass().setAll("message-success");
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
