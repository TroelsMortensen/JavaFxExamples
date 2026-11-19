package extraterrestrialexploration.presentation.controllers.planetmanagement;

import extraterrestrialexploration.domain.Planet;
import extraterrestrialexploration.persistence.DataManager;
import extraterrestrialexploration.presentation.core.ViewManager;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
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

public class ListPlanetsController implements Initializable
{
    @FXML
    private TableView<Planet> planetsTable;

    @FXML
    private TableColumn<Planet, Number> idColumn;

    @FXML
    private TableColumn<Planet, String> nameColumn;

    @FXML
    private TableColumn<Planet, String> climateColumn;

    @FXML
    private TableColumn<Planet, Number> distanceColumn;

    @FXML
    private TableColumn<Planet, Boolean> atmosphereColumn;

    @FXML
    private TableColumn<Planet, Boolean> lifeColumn;

    @FXML
    private Label messageLabel;

    private final DataManager dataManager;

    public ListPlanetsController(DataManager dataManager)
    {
        this.dataManager = dataManager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        idColumn.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getId()));
        nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        climateColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getClimateDescription()));
        distanceColumn.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getDistanceFromStarAU()));
        atmosphereColumn.setCellValueFactory(cell -> new SimpleBooleanProperty(cell.getValue().hasAtmosphere()));
        lifeColumn.setCellValueFactory(cell -> new SimpleBooleanProperty(cell.getValue().hasLife()));
        loadPlanets();
    }

    private void loadPlanets()
    {
        planetsTable.getItems().setAll(dataManager.getAllPlanets());
    }

    private Planet getSelectedPlanet()
    {
        Planet selected = planetsTable.getSelectionModel().getSelectedItem();
        if (selected == null)
        {
            showError("Please select a planet");
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
        ViewManager.showView("planetmanagement/AddPlanet");
    }

    @FXML
    private void handleShow()
    {
        Planet selected = getSelectedPlanet();
        if (selected != null)
        {
            ViewManager.showView("planetmanagement/ShowPlanet", String.valueOf(selected.getId()));
        }
    }

    @FXML
    private void handleUpdate()
    {
        Planet selected = getSelectedPlanet();
        if (selected != null)
        {
            ViewManager.showView("planetmanagement/UpdatePlanet", String.valueOf(selected.getId()));
        }
    }

    @FXML
    private void handleDelete()
    {
        Planet selected = getSelectedPlanet();
        if (selected == null)
        {
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Delete Planet");
        confirm.setHeaderText("Delete planet \"" + selected.getName() + "\"?");
        confirm.setContentText("This action cannot be undone.");
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK)
        {
            try
            {
                dataManager.deletePlanet(selected.getId());
                messageLabel.setText("Planet deleted successfully");
                messageLabel.getStyleClass().setAll("message-success");
                loadPlanets();
            }
            catch (IllegalStateException e)
            {
                showError(e.getMessage());
            }
        }
    }
}
