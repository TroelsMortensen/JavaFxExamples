package okayreads.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import okayreads.domain.Shelf;
import okayreads.persistence.DataManager;
import okayreads.presentation.core.ViewManager;
import okayreads.presentation.core.events.ChangeViewEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SelectShelfController implements Initializable
{
    @FXML
    private ListView<String> shelfListView;
    
    @FXML
    private Label messageLabel;
    
    private final DataManager dataManager;

    public SelectShelfController(DataManager dataManager)
    {
        this.dataManager = dataManager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        loadShelves();
    }
    
    private void loadShelves()
    {
        List<Shelf> shelves = dataManager.getAllShelves();
        shelfListView.getItems().clear();
        
        if (shelves.isEmpty())
        {
            messageLabel.setText("No shelves found. Please add a shelf first.");
            return;
        }
        
        for (Shelf shelf : shelves)
        {
            shelfListView.getItems().add(shelf.getName());
        }
    }
    
    @FXML
    private void handleViewShelf(ActionEvent evt)
    {
        String selectedShelfName = shelfListView.getSelectionModel().getSelectedItem();
        
        if (selectedShelfName == null)
        {
            messageLabel.setText("Please select a shelf");
            return;
        }

        Node source = (Node)evt.getSource();
        source.fireEvent(new ChangeViewEvent("ViewShelf", selectedShelfName));
    }
    
    @FXML
    private void handleBack(ActionEvent evt)
    {
        Node source = (Node)evt.getSource();
        source.fireEvent(new ChangeViewEvent("Home"));
    }
}

