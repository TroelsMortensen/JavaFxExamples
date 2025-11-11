import javafx.scene.control.TextField;

public class FirstViewController
{
    public TextField inputField;

    public void onPrintTextButtonPressed()
    {
        System.out.println(inputField.getText());
    }

    public void navigateToSecondView()
    {
        ViewManager.showView("SecondView");
    }
}
