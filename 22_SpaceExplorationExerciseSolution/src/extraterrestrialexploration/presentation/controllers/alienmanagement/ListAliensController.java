package extraterrestrialexploration.presentation.controllers.alienmanagement;

import extraterrestrialexploration.domain.Alien;
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

public class ListAliensController implements Initializable
{
    @FXML
    private TableView<Alien> aliensTable;

    @FXML
    private TableColumn<Alien, Number> idColumn;

    @FXML
    private TableColumn<Alien, String> nameColumn;

    @FXML
    private TableColumn<Alien, String> speciesColumn;

    @FXML
    private TableColumn<Alien, String> descriptionColumn;

    @FXML
    private Label messageLabel;

    private final DataManager dataManager;

    public ListAliensController(DataManager dataManager)
    {
        this.dataManager = dataManager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        idColumn.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getId()));
        nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        speciesColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getSpecies()));
        descriptionColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPhysicalDescription()));
        loadAliens();
    }

    private void loadAliens()
    {
        aliensTable.getItems().setAll(dataManager.getAllAliens());
    }

    private Alien getSelectedAlien()
    {
        Alien selected = aliensTable.getSelectionModel().getSelectedItem();
        if (selected == null)
        {
            showError("Please select an alien");
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
        ViewManager.showView("alienmanagement/AddAlien");
    }

    @FXML
    private void handleShow()
    {
        Alien selected = getSelectedAlien();
        if (selected != null)
        {
            ViewManager.showView("alienmanagement/ShowAlien", String.valueOf(selected.getId()));
        }
    }

    @FXML
    private void handleUpdate()
    {
        Alien selected = getSelectedAlien();
        if (selected != null)
        {
            ViewManager.showView("alienmanagement/UpdateAlien", String.valueOf(selected.getId()));
        }
    }

    @FXML
    private void handleDelete()
    {
        Alien selected = getSelectedAlien();
        if (selected == null)
        {
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Delete Alien");
        confirm.setHeaderText("Delete alien \"" + selected.getName() + "\"?");
        confirm.setContentText("This action cannot be undone.");
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK)
        {
            try
            {
                dataManager.deleteAlien(selected.getId());
                messageLabel.setText("Alien deleted successfully");
                messageLabel.getStyleClass().setAll("message-success");
                loadAliens();
            }
            catch (IllegalStateException e)
            {
                showError(e.getMessage());
            }
        }
    }
}
