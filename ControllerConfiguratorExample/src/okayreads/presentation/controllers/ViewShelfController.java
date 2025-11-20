package okayreads.presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import okayreads.domain.Book;
import okayreads.domain.Shelf;
import okayreads.persistence.DataManager;
import okayreads.persistence.ListDataManager;
import okayreads.presentation.core.AcceptsStringArgument;
import okayreads.presentation.core.ViewManager;

public class ViewShelfController implements AcceptsStringArgument
{
    @FXML
    private Label shelfTitleLabel;
    
    @FXML
    private TableView<Book> booksTable;
    
    @FXML
    private TableColumn<Book, String> isbnColumn;
    
    @FXML
    private TableColumn<Book, String> titleColumn;
    
    @FXML
    private TableColumn<Book, String> authorColumn;
    
    @FXML
    private TableColumn<Book, Integer> yearColumn;
    
    private DataManager dataManager;
    private String shelfName;
    
    public void init(DataManager dataManager)
    {
        this.dataManager = dataManager;
        
        // Set up table columns
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("publicationYear"));
    }
    
    @Override
    public void setArgument(String shelfName)
    {
        this.shelfName = shelfName;
        shelfTitleLabel.setText("Shelf: " + shelfName);
        loadBooks();
    }
    
    private void loadBooks()
    {
        Shelf shelf = dataManager.getShelfByName(shelfName);
        
        if (shelf != null)
        {
            booksTable.getItems().clear();
            
            for (Book book : shelf.getBooks())
            {
                booksTable.getItems().add(book);
            }
        }
    }
    
    @FXML
    private void handleBack()
    {
        ViewManager.showView("SelectShelf");
    }
}

