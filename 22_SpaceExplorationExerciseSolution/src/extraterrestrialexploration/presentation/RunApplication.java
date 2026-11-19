package extraterrestrialexploration.presentation;

import javafx.application.Application;
import javafx.stage.Stage;
import extraterrestrialexploration.presentation.core.ViewManager;

public class RunApplication extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ViewManager.init(primaryStage, "MainView");
        ViewManager.showView("Home");
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
