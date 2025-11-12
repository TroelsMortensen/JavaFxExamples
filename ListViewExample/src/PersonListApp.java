import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class PersonListApp extends Application {

    public static class Person {
        private final String firstName;
        private final String lastName;

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return firstName + " " + lastName;
        }
    }

    @Override
    public void start(Stage stage) {
        List<Person> people = Arrays.asList(
            new Person("Alice", "Andersen"),
            new Person("Bob", "Berg"),
            new Person("Charlie", "Christensen")
        );

        ListView<Person> listView = new ListView<>();
        listView.getItems().addAll(people); // manually add items

        VBox root = new VBox(listView);
        Scene scene = new Scene(root, 300, 200);

        stage.setTitle("Person List (Plain List)");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}