package extraterrestrialexploration.presentation.controllers.explorermanagement;

import extraterrestrialexploration.domain.Explorer;
import extraterrestrialexploration.persistence.DataManager;
import extraterrestrialexploration.presentation.core.AcceptsStringArgument;
import extraterrestrialexploration.presentation.core.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ShowExplorerController implements AcceptsStringArgument
{
    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label planetLabel;

    private final DataManager dataManager;

    public ShowExplorerController(DataManager dataManager)
    {
        this.dataManager = dataManager;
    }

    @Override
    public void setArgument(String argument)
    {
        int id = Integer.parseInt(argument);
        Explorer explorer = dataManager.getExplorer(id);
        if (explorer == null)
        {
            nameLabel.setText("Explorer not found");
            return;
        }

        idLabel.setText(String.valueOf(explorer.getId()));
        nameLabel.setText(explorer.getName());
        planetLabel.setText(explorer.getCurrentPlanet() != null ? explorer.getCurrentPlanet().getName() : "None");
    }

    @FXML
    private void handleBack()
    {
        ViewManager.showView("explorermanagement/ListExplorers");
    }
}
