package extraterrestrialexploration.presentation.controllers.encountermanagement;

import extraterrestrialexploration.domain.Encounter;
import extraterrestrialexploration.persistence.DataManager;
import extraterrestrialexploration.presentation.core.AcceptsStringArgument;
import extraterrestrialexploration.presentation.core.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ShowEncounterController implements AcceptsStringArgument
{
    @FXML
    private Label idLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label alienLabel;

    @FXML
    private Label planetLabel;

    @FXML
    private Label explorerLabel;

    @FXML
    private Label descriptionLabel;

    private final DataManager dataManager;

    public ShowEncounterController(DataManager dataManager)
    {
        this.dataManager = dataManager;
    }

    @Override
    public void setArgument(String argument)
    {
        int id = Integer.parseInt(argument);
        Encounter encounter = dataManager.getEncounter(id);
        if (encounter == null)
        {
            dateLabel.setText("Encounter not found");
            return;
        }

        idLabel.setText(String.valueOf(encounter.getId()));
        dateLabel.setText(encounter.getDate() != null ? encounter.getDate().toString() : "");
        alienLabel.setText(encounter.getAlienEncountered() != null ? encounter.getAlienEncountered().getName() : "None");
        planetLabel.setText(encounter.getOnPlanet() != null ? encounter.getOnPlanet().getName() : "None");
        explorerLabel.setText(encounter.getByExplorer() != null ? encounter.getByExplorer().getName() : "None");
        descriptionLabel.setText(encounter.getDescriptionOfTheEncounter());
    }

    @FXML
    private void handleBack()
    {
        ViewManager.showView("encountermanagement/ListEncounters");
    }
}
