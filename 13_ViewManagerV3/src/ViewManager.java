import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewManager
{
    private static Scene scene;

    public static void setScene(Scene mainScene)
    {
        scene = mainScene;
    }

    public static void init(Stage stage, String firstView) throws IOException
    {
        Parent root = FXMLLoader.load(ViewManager.class.getResource(firstView + ".fxml"));
        scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    public static void showView(String viewName)
    {
        try
        {
            Parent root = FXMLLoader.load(ViewManager.class.getResource(viewName + ".fxml"));
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
        loader.setLocation(ViewManager.class.getResource(viewName + ".fxml"));
        try
        {
            Parent root = loader.load();
            AcceptsStringArgument controller = loader.getController();
            controller.setArgument(argument);
            scene.setRoot(root);
        }
        catch (IOException e)
        {
            Alert error = new Alert(Alert.AlertType.ERROR, "Cannot find view: " + viewName);
            error.show();
        }
    }


    public static void showView(String viewName, Object argument)
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ViewManager.class.getResource(viewName + ".fxml"));
        try
        {
            Parent root = loader.load();
            AcceptsObjectArgument controller = loader.getController();
            controller.setArgument(argument);
            scene.setRoot(root);
        }
        catch (IOException e)
        {
            Alert error = new Alert(Alert.AlertType.ERROR, "Cannot find view: " + viewName);
            error.show();
        }
    }


}
