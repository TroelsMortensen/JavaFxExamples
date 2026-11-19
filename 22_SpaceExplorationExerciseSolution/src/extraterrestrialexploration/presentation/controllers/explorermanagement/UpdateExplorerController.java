package extraterrestrialexploration.presentation.controllers.explorermanagement;

import extraterrestrialexploration.domain.Explorer;
import extraterrestrialexploration.domain.Planet;
import extraterrestrialexploration.persistence.DataManager;
import extraterrestrialexploration.presentation.core.AcceptsStringArgument;
import extraterrestrialexploration.presentation.core.ViewManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateExplorerController implements AcceptsStringArgument, Initializable
{
    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<Planet> planetComboBox;

    @FXML
    private Label messageLabel;

    private final DataManager dataManager;
    private int explorerId;

    public UpdateExplorerController(DataManager dataManager)
    {
        this.dataManager = dataManager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        planetComboBox.setConverter(new StringConverter<>()
        {
            @Override
            public String toString(Planet planet)
            {
                return planet == null ? "" : planet.getName();
            }

            @Override
            public Planet fromString(String string)
            {
                return null;
            }
        });
        planetComboBox.getItems().setAll(dataManager.getAllPlanets());
    }

    @Override
    public void setArgument(String argument)
    {
        explorerId = Integer.parseInt(argument);
        Explorer explorer = dataManager.getExplorer(explorerId);
        if (explorer == null)
        {
            showError("Explorer not found");
            return;
        }

        nameField.setText(explorer.getName());
        if (explorer.getCurrentPlanet() != null)
        {
            for (Planet planet : planetComboBox.getItems())
            {
                if (planet.getId() == explorer.getCurrentPlanet().getId())
                {
                    planetComboBox.setValue(planet);
                    break;
                }
            }
        }
    }

    @FXML
    private void handleSave()
    {
        String name = nameField.getText().trim();
        Planet planet = planetComboBox.getValue();

        if (name.isEmpty())
        {
            showError("Please enter a name");
            return;
        }
        if (planet == null)
        {
            showError("Please select a planet");
            return;
        }

        Explorer explorer = new Explorer(name, planet);
        explorer.setId(explorerId);
        dataManager.updateExplorer(explorer);

        messageLabel.setText("Explorer updated successfully!");
        messageLabel.getStyleClass().setAll("message-success");
    }

    @FXML
    private void handleCancel()
    {
        ViewManager.showView("explorermanagement/ListExplorers");
    }

    private void showError(String message)
    {
        messageLabel.setText(message);
        messageLabel.getStyleClass().setAll("message-error");
    }
}
