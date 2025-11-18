package okayreads.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import okayreads.domain.Shelf;
import okayreads.persistence.DataManager;
import okayreads.persistence.ListDataManager;
import okayreads.presentation.core.ViewManager;

import java.net.URL;
import java.util.ResourceBundle;

public class AddShelfController implements Initializable
{
    @FXML
    private TextField nameField;
    
    @FXML
    private Label messageLabel;
    
    private DataManager dataManager;
    
    @FXML
    public void initialize(URL location, ResourceBundle resources)
    {
        dataManager = new ListDataManager();
    }
    
    @FXML
    private void handleAddShelf()
    {
        String name = nameField.getText().trim();
        
        if (name.isEmpty())
        {
            messageLabel.setText("Please enter a shelf name");
            messageLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            return;
        }
        
        Shelf shelf = new Shelf(name);
        dataManager.addShelf(shelf);
        
        messageLabel.setText("Shelf added successfully!");
        messageLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
        nameField.clear();
    }
    
    @FXML
    private void handleCancel()
    {
        ViewManager.showView("Home");
    }
}

