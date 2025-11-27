package okayreads.presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import okayreads.domain.Shelf;
import okayreads.persistence.DataManager;
import okayreads.persistence.ListDataManager;
import okayreads.presentation.core.ViewManager;

import java.util.List;

public class SelectShelfController
{
    @FXML
    private ListView<String> shelfListView;
    
    @FXML
    private Label messageLabel;
    
    private DataManager dataManager;
    
    public void init(DataManager dataManager)
    {
        this.dataManager = dataManager;
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
    private void handleViewShelf()
    {
        String selectedShelfName = shelfListView.getSelectionModel().getSelectedItem();
        
        if (selectedShelfName == null)
        {
            messageLabel.setText("Please select a shelf");
            return;
        }
        
        ViewManager.showView("ViewShelf", selectedShelfName);
    }
    
    @FXML
    private void handleBack()
    {
        ViewManager.showView("Home");
    }
}

