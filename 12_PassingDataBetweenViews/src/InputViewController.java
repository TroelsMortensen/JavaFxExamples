import javafx.scene.control.TextField;

public class InputViewController
{
    public TextField inputField;

    public void openDisplayView()
    {
        ViewManager.showView("DisplayView", inputField.getText());
    }
}
