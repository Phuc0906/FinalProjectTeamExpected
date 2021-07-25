package sample;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;


public class Main extends Application {


    @Override
    public void start (Stage primaryStage) throws Exception{
        BorderPane pane = FXMLLoader.load(getClass().getResource("pageFXML/SplashScreen.fxml"));
        primaryStage.setScene(new Scene(pane, 1000, 720));
        primaryStage.show();
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);
        fadeIn.play();

        fadeIn.setOnFinished(e -> {
            fadeOut.play();
        });
        fadeOut.setOnFinished(e -> {
            Parent homePage = null;
            try {
                homePage = FXMLLoader.load(getClass().getResource("pageFXML/HomePage.fxml"));
                primaryStage.setTitle("Projekt Red");
                primaryStage.setScene(new Scene(homePage,1000, 720));
                primaryStage.show();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }
}
