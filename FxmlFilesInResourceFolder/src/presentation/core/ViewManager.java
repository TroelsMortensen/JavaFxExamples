package presentation.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewManager
{
    private static Scene scene;

    private static final String fxmlDirectoryPath = "/fxml/";

    public static void init(Stage primaryStage, String initialView) throws IOException
    {
        Parent root = FXMLLoader.load(
            ViewManager.class.getResource(fxmlDirectoryPath + initialView + ".fxml")
        );
        scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showView(String viewName)
    {
        try
        {
            Parent root = FXMLLoader.load(ViewManager.class.getResource(fxmlDirectoryPath + viewName + ".fxml"));
            scene.setRoot(root);
        }
        catch (Exception e)
        {
            Alert error = new Alert(Alert.AlertType.ERROR, "Cannot find view: " + viewName);
            error.show();
        }
    }

    public static void showView(String viewName, String argument)
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ViewManager.class.getResource(fxmlDirectoryPath + viewName + ".fxml"));
        try
        {
            Parent root = loader.load();
            AcceptsStringArgument controller = loader.getController();
            controller.setArgument(argument);
            scene.setRoot(root);
        }
        catch (Exception e)
        {
            Alert error = new Alert(Alert.AlertType.ERROR, "Cannot find view: " + viewName);
            error.show();
        }
    }

    public static void showView(String viewName, Object argument)
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ViewManager.class.getResource(fxmlDirectoryPath + viewName + ".fxml"));
        try
        {
            Parent root = loader.load();
            AcceptsObjectArgument controller = loader.getController();
            controller.setArgument(argument);
            scene.setRoot(root);
        }
        catch (Exception e)
        {
            Alert error = new Alert(Alert.AlertType.ERROR, "Cannot find view: " + viewName);
            error.show();
        }
    }
}
