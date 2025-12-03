package okayreads.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import okayreads.domain.Shelf;
import okayreads.persistence.DataManager;
import okayreads.persistence.ListDataManager;
import okayreads.presentation.core.ViewManager;
import okayreads.presentation.core.events.ChangeViewEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AddShelfController
{
    @FXML
    private TextField nameField;
    
    @FXML
    private Label messageLabel;
    
    private final DataManager dataManager;

    public AddShelfController(DataManager dataManager)
    {
        this.dataManager = dataManager;
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
    private void handleCancel(ActionEvent evt)
    {
        Node source = (Node)evt.getSource();
        source.fireEvent(new ChangeViewEvent("Home"));
    }
}

