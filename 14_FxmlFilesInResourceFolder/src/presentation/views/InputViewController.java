package presentation.views;

import javafx.scene.control.TextField;
import presentation.core.ViewManager;

public class InputViewController
{
    public TextField inputField;

    public void openDisplayView()
    {
        ViewManager.showView("DisplayView", inputField.getText());
    }
}
