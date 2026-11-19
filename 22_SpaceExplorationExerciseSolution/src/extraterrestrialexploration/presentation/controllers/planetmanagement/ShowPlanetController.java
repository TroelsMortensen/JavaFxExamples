package extraterrestrialexploration.presentation.controllers.planetmanagement;

import extraterrestrialexploration.domain.Planet;
import extraterrestrialexploration.persistence.DataManager;
import extraterrestrialexploration.presentation.core.AcceptsStringArgument;
import extraterrestrialexploration.presentation.core.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ShowPlanetController implements AcceptsStringArgument
{
    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label climateLabel;

    @FXML
    private Label distanceLabel;

    @FXML
    private Label atmosphereLabel;

    @FXML
    private Label lifeLabel;

    private final DataManager dataManager;

    public ShowPlanetController(DataManager dataManager)
    {
        this.dataManager = dataManager;
    }

    @Override
    public void setArgument(String argument)
    {
        int id = Integer.parseInt(argument);
        Planet planet = dataManager.getPlanet(id);
        if (planet == null)
        {
            nameLabel.setText("Planet not found");
            return;
        }

        idLabel.setText(String.valueOf(planet.getId()));
        nameLabel.setText(planet.getName());
        climateLabel.setText(planet.getClimateDescription());
        distanceLabel.setText(planet.getDistanceFromStarAU() + " AU");
        atmosphereLabel.setText(planet.hasAtmosphere() ? "Yes" : "No");
        lifeLabel.setText(planet.hasLife() ? "Yes" : "No");
    }

    @FXML
    private void handleBack()
    {
        ViewManager.showView("planetmanagement/ListPlanets");
    }
}
