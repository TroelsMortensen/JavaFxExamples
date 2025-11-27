package presentation.views;

import javafx.scene.control.Label;
import presentation.core.AcceptsObjectArgument;
import presentation.core.AcceptsStringArgument;
import presentation.core.ViewManager;

public class DisplayViewController implements AcceptsStringArgument, AcceptsObjectArgument
{
    public Label displayLabel;

    public void backToInputView()
    {
        ViewManager.showView("InputView");
    }

    @Override
    public void setArgument(String argument)
    {
        displayLabel.setText(argument);
    }

    @Override
    public void setArgument(Object argument)
    {
        // do stuff...
    }
}
