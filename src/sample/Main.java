package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.TopPane;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: white");

        TopPane topPane = new TopPane(borderPane);
        borderPane.setTop(topPane);

        borderPane.setBottom(new Footer());
        borderPane.setCenter(new ImportantNews(borderPane));
        Scene scene = new Scene(borderPane, 1500, 1300);
        primaryStage.setTitle("Demo Web Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}