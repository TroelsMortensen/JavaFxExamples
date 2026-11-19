package extraterrestrialexploration.presentation.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewManager
{
    private static BorderPane mainLayout;
    private final static String fxmlDirectoryPath = "/fxml/";

    public static void init(Stage primaryStage, String initialView) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ViewManager.class.getResource(fxmlDirectoryPath + initialView + ".fxml"));
        loader.setControllerFactory(new ControllerFactory());
        BorderPane root = loader.load();

        mainLayout = root;

        Scene scene = new Scene(root, 1000, 650);
        scene.getStylesheets().add(ViewManager.class.getResource("/css/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Extraterrestrial Exploration");
        primaryStage.show();
    }

    public static void showView(String viewName)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewManager.class.getResource(fxmlDirectoryPath + viewName + ".fxml"));
            loader.setControllerFactory(new ControllerFactory());
            Parent root = loader.load();
            mainLayout.setCenter(root);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Alert error = new Alert(Alert.AlertType.ERROR, "Cannot find view: " + viewName);
            error.show();
        }
    }

    public static void showView(String viewName, String argument)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewManager.class.getResource(fxmlDirectoryPath + viewName + ".fxml"));
            loader.setControllerFactory(new ControllerFactory());
            Parent root = loader.load();

            AcceptsStringArgument controller = loader.getController();
            controller.setArgument(argument);
            mainLayout.setCenter(root);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Alert error = new Alert(Alert.AlertType.ERROR, "Cannot find view: " + viewName);
            error.show();
        }
    }
}
