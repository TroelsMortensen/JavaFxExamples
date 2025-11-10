import javafx.application.Application;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

public class RunApp extends Application
{
    @Override public void start(Stage primaryStage) throws Exception
    {
        CheckBox checkBox = new CheckBox("Check this out");

        boolean isSelected = checkBox.isSelected();
        boolean isIndeterminate = checkBox.isIndeterminate();
        boolean thisCheckBoxAllowsIndeterminateState = checkBox.isAllowIndeterminate();
        checkBox.setSelected(true);
        checkBox.setAllowIndeterminate(false);
    }
}
