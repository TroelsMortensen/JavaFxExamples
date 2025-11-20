package okayreads.presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import okayreads.domain.Author;
import okayreads.domain.Book;
import okayreads.domain.Shelf;
import okayreads.persistence.DataManager;
import okayreads.persistence.ListDataManager;
import okayreads.presentation.core.ViewManager;

import java.util.List;

public class AddBookController
{
    @FXML
    private TextField isbnField;
    
    @FXML
    private TextField titleField;
    
    @FXML
    private TextField yearField;
    
    @FXML
    private ComboBox<String> authorComboBox;
    
    @FXML
    private ComboBox<String> shelfComboBox;
    
    @FXML
    private Label messageLabel;
    
    private DataManager dataManager;
    
    public void init(DataManager dataManager)
    {
        this.dataManager = dataManager;
        loadAuthors();
        loadShelves();
    }
    
    private void loadAuthors()
    {
        List<Author> authors = dataManager.getAllAuthors();
        authorComboBox.getItems().clear();
        
        for (Author author : authors)
        {
            authorComboBox.getItems().add(author.getName());
        }
    }
    
    private void loadShelves()
    {
        List<Shelf> shelves = dataManager.getAllShelves();
        shelfComboBox.getItems().clear();
        
        for (Shelf shelf : shelves)
        {
            shelfComboBox.getItems().add(shelf.getName());
        }
    }
    
    @FXML
    private void handleAddBook()
    {
        String isbn = isbnField.getText().trim();
        String title = titleField.getText().trim();
        String yearText = yearField.getText().trim();
        String authorName = authorComboBox.getValue();
        String shelfName = shelfComboBox.getValue();
        
        // Validation
        if (isbn.isEmpty() || title.isEmpty() || yearText.isEmpty())
        {
            messageLabel.setText("Please fill in all required fields");
            messageLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            return;
        }
        
        if (authorName == null)
        {
            messageLabel.setText("Please select an author");
            messageLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            return;
        }
        
        int year;
        try
        {
            year = Integer.parseInt(yearText);
        }
        catch (NumberFormatException e)
        {
            messageLabel.setText("Year must be a valid number");
            messageLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            return;
        }
        
        // Find the author object
        Author selectedAuthor = null;
        for (Author author : dataManager.getAllAuthors())
        {
            if (author.getName().equals(authorName))
            {
                selectedAuthor = author;
                break;
            }
        }
        
        // Create and add book
        Book book = new Book(isbn, title, year, selectedAuthor);
        dataManager.addBook(book);
        
        // Add to shelf if selected
        if (shelfName != null)
        {
            Shelf shelf = dataManager.getShelfByName(shelfName);
            if (shelf != null)
            {
                shelf.addBook(book);
            }
        }
        
        messageLabel.setText("Book added successfully!");
        messageLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
        
        // Clear fields
        isbnField.clear();
        titleField.clear();
        yearField.clear();
        authorComboBox.setValue(null);
        shelfComboBox.setValue(null);
    }
    
    @FXML
    private void handleCancel()
    {
        ViewManager.showView("Home");
    }
}

