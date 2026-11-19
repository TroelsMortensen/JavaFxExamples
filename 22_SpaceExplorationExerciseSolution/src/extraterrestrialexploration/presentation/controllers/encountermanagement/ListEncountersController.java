package extraterrestrialexploration.presentation.controllers.encountermanagement;

import extraterrestrialexploration.domain.Encounter;
import extraterrestrialexploration.persistence.DataManager;
import extraterrestrialexploration.presentation.core.ViewManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListEncountersController implements Initializable
{
    @FXML
    private TableView<Encounter> encountersTable;

    @FXML
    private TableColumn<Encounter, Number> idColumn;

    @FXML
    private TableColumn<Encounter, String> dateColumn;

    @FXML
    private TableColumn<Encounter, String> alienColumn;

    @FXML
    private TableColumn<Encounter, String> planetColumn;

    @FXML
    private TableColumn<Encounter, String> explorerColumn;

    @FXML
    private Label messageLabel;

    private final DataManager dataManager;

    public ListEncountersController(DataManager dataManager)
    {
        this.dataManager = dataManager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        idColumn.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getId()));
        dateColumn.setCellValueFactory(cell -> new SimpleStringProperty(
                cell.getValue().getDate() != null ? cell.getValue().getDate().toString() : ""));
        alienColumn.setCellValueFactory(cell -> new SimpleStringProperty(
                cell.getValue().getAlienEncountered() != null ? cell.getValue().getAlienEncountered().getName() : "None"));
        planetColumn.setCellValueFactory(cell -> new SimpleStringProperty(
                cell.getValue().getOnPlanet() != null ? cell.getValue().getOnPlanet().getName() : "None"));
        explorerColumn.setCellValueFactory(cell -> new SimpleStringProperty(
                cell.getValue().getByExplorer() != null ? cell.getValue().getByExplorer().getName() : "None"));
        loadEncounters();
    }

    private void loadEncounters()
    {
        encountersTable.getItems().setAll(dataManager.getAllEncounters());
    }

    private Encounter getSelectedEncounter()
    {
        Encounter selected = encountersTable.getSelectionModel().getSelectedItem();
        if (selected == null)
        {
            showError("Please select an encounter");
        }
        return selected;
    }

    private void showError(String message)
    {
        messageLabel.setText(message);
        messageLabel.getStyleClass().setAll("message-error");
    }

    @FXML
    private void handleAdd()
    {
        ViewManager.showView("encountermanagement/AddEncounter");
    }

    @FXML
    private void handleShow()
    {
        Encounter selected = getSelectedEncounter();
        if (selected != null)
        {
            ViewManager.showView("encountermanagement/ShowEncounter", String.valueOf(selected.getId()));
        }
    }

    @FXML
    private void handleUpdate()
    {
        Encounter selected = getSelectedEncounter();
        if (selected != null)
        {
            ViewManager.showView("encountermanagement/UpdateEncounter", String.valueOf(selected.getId()));
        }
    }

    @FXML
    private void handleDelete()
    {
        Encounter selected = getSelectedEncounter();
        if (selected == null)
        {
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Delete Encounter");
        confirm.setHeaderText("Delete encounter #" + selected.getId() + "?");
        confirm.setContentText("This action cannot be undone.");
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK)
        {
            dataManager.deleteEncounter(selected.getId());
            messageLabel.setText("Encounter deleted successfully");
            messageLabel.getStyleClass().setAll("message-success");
            loadEncounters();
        }
    }
}
