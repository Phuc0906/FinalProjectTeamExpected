package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import sample.SupportClass.SupportedMethod;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/NewPage(Phong).fxml"));
        Parent homePage = loader.load();

        NewController newController = loader.getController();
        newController.setTitle();
        newController.setDescription();
        newController.setImgList();

        primaryStage.setTitle("Projekt Red");
        primaryStage.setScene(new Scene(homePage));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> closeApp(primaryStage));

    }

    public void closeApp(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close App");
        alert.setContentText("Do you want to close the app?");
        alert.setHeaderText("You are trying to close the app");

        if(alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }
}