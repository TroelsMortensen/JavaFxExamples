package extraterrestrialexploration.presentation.controllers.explorermanagement;

import extraterrestrialexploration.domain.Explorer;
import extraterrestrialexploration.domain.Planet;
import extraterrestrialexploration.persistence.DataManager;
import extraterrestrialexploration.presentation.core.ViewManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class AddExplorerController implements Initializable
{
    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<Planet> planetComboBox;

    @FXML
    private Label messageLabel;

    private final DataManager dataManager;

    public AddExplorerController(DataManager dataManager)
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

        dataManager.addExplorer(new Explorer(name, planet));
        messageLabel.setText("Explorer added successfully!");
        messageLabel.getStyleClass().setAll("message-success");

        nameField.clear();
        planetComboBox.setValue(null);
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
