package extraterrestrialexploration.presentation.controllers.explorermanagement;

import extraterrestrialexploration.domain.Explorer;
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

public class ListExplorersController implements Initializable
{
    @FXML
    private TableView<Explorer> explorersTable;

    @FXML
    private TableColumn<Explorer, Number> idColumn;

    @FXML
    private TableColumn<Explorer, String> nameColumn;

    @FXML
    private TableColumn<Explorer, String> planetColumn;

    @FXML
    private Label messageLabel;

    private final DataManager dataManager;

    public ListExplorersController(DataManager dataManager)
    {
        this.dataManager = dataManager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        idColumn.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getId()));
        nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        planetColumn.setCellValueFactory(cell -> new SimpleStringProperty(
                cell.getValue().getCurrentPlanet() != null ? cell.getValue().getCurrentPlanet().getName() : "None"));
        loadExplorers();
    }

    private void loadExplorers()
    {
        explorersTable.getItems().setAll(dataManager.getAllExplorers());
    }

    private Explorer getSelectedExplorer()
    {
        Explorer selected = explorersTable.getSelectionModel().getSelectedItem();
        if (selected == null)
        {
            showError("Please select an explorer");
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
        ViewManager.showView("explorermanagement/AddExplorer");
    }

    @FXML
    private void handleShow()
    {
        Explorer selected = getSelectedExplorer();
        if (selected != null)
        {
            ViewManager.showView("explorermanagement/ShowExplorer", String.valueOf(selected.getId()));
        }
    }

    @FXML
    private void handleUpdate()
    {
        Explorer selected = getSelectedExplorer();
        if (selected != null)
        {
            ViewManager.showView("explorermanagement/UpdateExplorer", String.valueOf(selected.getId()));
        }
    }

    @FXML
    private void handleMove()
    {
        Explorer selected = getSelectedExplorer();
        if (selected != null)
        {
            ViewManager.showView("explorermanagement/MoveExplorer", String.valueOf(selected.getId()));
        }
    }

    @FXML
    private void handleDelete()
    {
        Explorer selected = getSelectedExplorer();
        if (selected == null)
        {
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Delete Explorer");
        confirm.setHeaderText("Delete explorer \"" + selected.getName() + "\"?");
        confirm.setContentText("This action cannot be undone.");
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK)
        {
            try
            {
                dataManager.deleteExplorer(selected.getId());
                messageLabel.setText("Explorer deleted successfully");
                messageLabel.getStyleClass().setAll("message-success");
                loadExplorers();
            }
            catch (IllegalStateException e)
            {
                showError(e.getMessage());
            }
        }
    }
}
