package okayreads.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import okayreads.presentation.core.ViewManager;
import okayreads.presentation.core.events.ChangeViewEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable
{
    @FXML
    private BorderPane shell;

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

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        shell.addEventHandler(ChangeViewEvent.TYPE,this::changeView);
    }

    private void changeView(ChangeViewEvent evt)
    {
        ViewManager.showView(evt.getViewName(), evt.getArg());
    }
}

