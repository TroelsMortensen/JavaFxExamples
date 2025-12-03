package okayreads.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import okayreads.domain.Author;
import okayreads.persistence.DataManager;
import okayreads.presentation.core.ViewManager;
import okayreads.presentation.core.events.ChangeViewEvent;

public class AddAuthorController
{
    @FXML
    private TextField nameField;
    
    @FXML
    private Label messageLabel;
    
    private final DataManager dataManager;

    public AddAuthorController(DataManager dataManager)
    {
        this.dataManager = dataManager;
    }

    @FXML
    private void handleAddAuthor()
    {
        String name = nameField.getText().trim();
        
        if (name.isEmpty())
        {
            messageLabel.setText("Please enter an author name");
            messageLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            return;
        }
        
        Author author = new Author(name);
        dataManager.addAuthor(author);
        
        messageLabel.setText("Author added successfully!");
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

