import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewManager
{
    private static Stage stage;

    public static void setStage(Stage primaryStage)
    {
        stage = primaryStage;
    }

    public static void showView(String viewName)
    {
        try
        {
            Parent root = FXMLLoader.load(ViewManager.class.getResource(viewName + ".fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
        catch (IOException e)
        {
            Alert error = new Alert(Alert.AlertType.ERROR, "Cannot find view: " + viewName);
            error.show();
        }
    }
}
