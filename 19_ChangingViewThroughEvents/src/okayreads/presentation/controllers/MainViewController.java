package okayreads.presentation.controllers;

import javafx.fxml.FXML;
import okayreads.presentation.core.ViewManager;

public class MainViewController
{
    @FXML
    private void handleAddAuthor()
    {
        ViewManager.showView("AddAuthor");
    }

    @FXML
    private void handleAddBook()
    {
        ViewManager.showView("AddBook");
    }

    @FXML
    private void handleAddShelf()
    {
        ViewManager.showView("AddShelf");
    }

    @FXML
    private void handleViewShelves()
    {
        ViewManager.showView("SelectShelf");
    }
}

