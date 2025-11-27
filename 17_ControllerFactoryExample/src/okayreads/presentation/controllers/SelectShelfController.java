package okayreads.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import okayreads.domain.Shelf;
import okayreads.persistence.DataManager;
import okayreads.persistence.ListDataManager;
import okayreads.presentation.core.ViewManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SelectShelfController implements Initializable
{
    @FXML
    private ListView<String> shelfListView;
    
    @FXML
    private Label messageLabel;
    
    private DataManager dataManager;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        dataManager = new ListDataManager();
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

