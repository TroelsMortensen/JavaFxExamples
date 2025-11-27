package okayreads.presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import okayreads.domain.Author;
import okayreads.persistence.DataManager;
import okayreads.persistence.ListDataManager;
import okayreads.presentation.core.ViewManager;

public class AddAuthorController
{
    @FXML
    private TextField nameField;
    
    @FXML
    private Label messageLabel;
    
    private DataManager dataManager;

    public void init(DataManager dataManager)
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
    private void handleCancel()
    {
        ViewManager.showView("Home");
    }
}

