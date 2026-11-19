package extraterrestrialexploration.presentation.controllers.encountermanagement;

import extraterrestrialexploration.domain.Alien;
import extraterrestrialexploration.domain.Encounter;
import extraterrestrialexploration.domain.Explorer;
import extraterrestrialexploration.domain.Planet;
import extraterrestrialexploration.persistence.DataManager;
import extraterrestrialexploration.presentation.core.AcceptsStringArgument;
import extraterrestrialexploration.presentation.core.ViewManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UpdateEncounterController implements AcceptsStringArgument, Initializable
{
    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<Alien> alienComboBox;

    @FXML
    private ComboBox<Planet> planetComboBox;

    @FXML
    private ComboBox<Explorer> explorerComboBox;

    @FXML
    private TextArea descriptionField;

    @FXML
    private Label messageLabel;

    private final DataManager dataManager;
    private int encounterId;

    public UpdateEncounterController(DataManager dataManager)
    {
        this.dataManager = dataManager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        alienComboBox.setConverter(new StringConverter<>()
        {
            @Override
            public String toString(Alien alien)
            {
                return alien == null ? "" : alien.getName();
            }

            @Override
            public Alien fromString(String string)
            {
                return null;
            }
        });
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
        explorerComboBox.setConverter(new StringConverter<>()
        {
            @Override
            public String toString(Explorer explorer)
            {
                return explorer == null ? "" : explorer.getName();
            }

            @Override
            public Explorer fromString(String string)
            {
                return null;
            }
        });

        alienComboBox.getItems().setAll(dataManager.getAllAliens());
        planetComboBox.getItems().setAll(dataManager.getAllPlanets());
        explorerComboBox.getItems().setAll(dataManager.getAllExplorers());
    }

    @Override
    public void setArgument(String argument)
    {
        encounterId = Integer.parseInt(argument);
        Encounter encounter = dataManager.getEncounter(encounterId);
        if (encounter == null)
        {
            showError("Encounter not found");
            return;
        }

        datePicker.setValue(encounter.getDate());
        descriptionField.setText(encounter.getDescriptionOfTheEncounter());

        if (encounter.getAlienEncountered() != null)
        {
            for (Alien alien : alienComboBox.getItems())
            {
                if (alien.getId() == encounter.getAlienEncountered().getId())
                {
                    alienComboBox.setValue(alien);
                    break;
                }
            }
        }
        if (encounter.getOnPlanet() != null)
        {
            for (Planet planet : planetComboBox.getItems())
            {
                if (planet.getId() == encounter.getOnPlanet().getId())
                {
                    planetComboBox.setValue(planet);
                    break;
                }
            }
        }
        if (encounter.getByExplorer() != null)
        {
            for (Explorer explorer : explorerComboBox.getItems())
            {
                if (explorer.getId() == encounter.getByExplorer().getId())
                {
                    explorerComboBox.setValue(explorer);
                    break;
                }
            }
        }
    }

    @FXML
    private void handleSave()
    {
        LocalDate date = datePicker.getValue();
        Alien alien = alienComboBox.getValue();
        Planet planet = planetComboBox.getValue();
        Explorer explorer = explorerComboBox.getValue();
        String description = descriptionField.getText().trim();

        if (date == null || alien == null || planet == null || explorer == null || description.isEmpty())
        {
            showError("Please fill in all required fields");
            return;
        }

        Encounter encounter = new Encounter(date, alien, planet, explorer, description);
        encounter.setId(encounterId);
        dataManager.updateEncounter(encounter);

        messageLabel.setText("Encounter updated successfully!");
        messageLabel.getStyleClass().setAll("message-success");
    }

    @FXML
    private void handleCancel()
    {
        ViewManager.showView("encountermanagement/ListEncounters");
    }

    private void showError(String message)
    {
        messageLabel.setText(message);
        messageLabel.getStyleClass().setAll("message-error");
    }
}
