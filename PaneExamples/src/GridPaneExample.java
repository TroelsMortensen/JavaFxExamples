import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GridPaneExample extends Application
{
    @Override public void start(Stage primaryStage) throws Exception
    {
        GridPane grid = new GridPane();
        grid.add(new Label("Hello"), 0,0);
        grid.add(new Label("Hello"), 1,1);
        grid.add(new Label("Hello"), 2,2);
        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
