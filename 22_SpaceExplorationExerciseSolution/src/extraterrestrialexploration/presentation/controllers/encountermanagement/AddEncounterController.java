package extraterrestrialexploration.presentation.controllers.encountermanagement;

import extraterrestrialexploration.domain.Alien;
import extraterrestrialexploration.domain.Encounter;
import extraterrestrialexploration.domain.Explorer;
import extraterrestrialexploration.domain.Planet;
import extraterrestrialexploration.persistence.DataManager;
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

public class AddEncounterController implements Initializable
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

    public AddEncounterController(DataManager dataManager)
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
        datePicker.setValue(LocalDate.now());
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

        dataManager.addEncounter(new Encounter(date, alien, planet, explorer, description));
        messageLabel.setText("Encounter added successfully!");
        messageLabel.getStyleClass().setAll("message-success");

        datePicker.setValue(LocalDate.now());
        alienComboBox.setValue(null);
        planetComboBox.setValue(null);
        explorerComboBox.setValue(null);
        descriptionField.clear();
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
