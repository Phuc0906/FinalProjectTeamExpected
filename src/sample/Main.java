package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception{
        Parent homePage = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        primaryStage.setTitle("Projekt Red");
        primaryStage.setScene(new Scene(homePage,1000, 720));
        primaryStage.show();
    }
}