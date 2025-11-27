import javafx.scene.control.Label;

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
//        MyData data = (MyData) argument;
        // ... stuff
    }
}
