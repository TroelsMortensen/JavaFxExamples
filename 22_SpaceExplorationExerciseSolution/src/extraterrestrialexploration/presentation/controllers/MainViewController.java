package extraterrestrialexploration.presentation.controllers;

import javafx.fxml.FXML;
import extraterrestrialexploration.presentation.core.ViewManager;

public class MainViewController
{
    @FXML
    private void handleHome()
    {
        ViewManager.showView("Home");
    }

    @FXML
    private void handlePlanets()
    {
        ViewManager.showView("planetmanagement/ListPlanets");
    }

    @FXML
    private void handleExplorers()
    {
        ViewManager.showView("explorermanagement/ListExplorers");
    }

    @FXML
    private void handleAliens()
    {
        ViewManager.showView("alienmanagement/ListAliens");
    }

    @FXML
    private void handleEncounters()
    {
        ViewManager.showView("encountermanagement/ListEncounters");
    }
}
