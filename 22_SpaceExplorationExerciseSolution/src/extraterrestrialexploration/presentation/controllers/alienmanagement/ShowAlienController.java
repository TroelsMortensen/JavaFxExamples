package extraterrestrialexploration.presentation.controllers.alienmanagement;

import extraterrestrialexploration.domain.Alien;
import extraterrestrialexploration.persistence.DataManager;
import extraterrestrialexploration.presentation.core.AcceptsStringArgument;
import extraterrestrialexploration.presentation.core.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ShowAlienController implements AcceptsStringArgument
{
    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label speciesLabel;

    @FXML
    private Label descriptionLabel;

    private final DataManager dataManager;

    public ShowAlienController(DataManager dataManager)
    {
        this.dataManager = dataManager;
    }

    @Override
    public void setArgument(String argument)
    {
        int id = Integer.parseInt(argument);
        Alien alien = dataManager.getAlien(id);
        if (alien == null)
        {
            nameLabel.setText("Alien not found");
            return;
        }

        idLabel.setText(String.valueOf(alien.getId()));
        nameLabel.setText(alien.getName());
        speciesLabel.setText(alien.getSpecies());
        descriptionLabel.setText(alien.getPhysicalDescription());
    }

    @FXML
    private void handleBack()
    {
        ViewManager.showView("alienmanagement/ListAliens");
    }
}
