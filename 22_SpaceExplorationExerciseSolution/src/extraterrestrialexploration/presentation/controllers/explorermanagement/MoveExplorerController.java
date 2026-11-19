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
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class MoveExplorerController implements AcceptsStringArgument, Initializable
{
    @FXML
    private Label nameLabel;

    @FXML
    private Label currentPlanetLabel;

    @FXML
    private ComboBox<Planet> planetComboBox;

    @FXML
    private Label messageLabel;

    private final DataManager dataManager;
    private Explorer explorer;

    public MoveExplorerController(DataManager dataManager)
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
        int id = Integer.parseInt(argument);
        explorer = dataManager.getExplorer(id);
        if (explorer == null)
        {
            showError("Explorer not found");
            return;
        }

        nameLabel.setText(explorer.getName());
        currentPlanetLabel.setText(explorer.getCurrentPlanet() != null ? explorer.getCurrentPlanet().getName() : "None");
    }

    @FXML
    private void handleSave()
    {
        if (explorer == null)
        {
            showError("Explorer not found");
            return;
        }

        Planet planet = planetComboBox.getValue();
        if (planet == null)
        {
            showError("Please select a destination planet");
            return;
        }

        explorer.setCurrentPlanet(planet);
        dataManager.updateExplorer(explorer);

        currentPlanetLabel.setText(planet.getName());
        messageLabel.setText("Explorer moved successfully!");
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
