package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: white;");

        // set the template title for the main page
        // use the custom class for adding to all other scene
        TopPane topPane = new TopPane(borderPane);
        borderPane.setTop(topPane);

        // adding developer name at the bottom
        borderPane.setBottom(new Footer());

        // add important news to the center
        borderPane.setCenter(new ImportantNews(borderPane));

        // add pane to scene and show
        Scene scene = new Scene(borderPane, 1500, 1300);
        primaryStage.setTitle("Demo Web Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}