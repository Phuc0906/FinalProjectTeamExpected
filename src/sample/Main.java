/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 2021B
  Assessment: Final Project
  Created  date: 17/7/2021
  Author: Nguyen Hung Anh s3877798
    Hoang Phuc s3879362
    Le Tan Phong s3877819
    Thai Thuan s3877024
  Last modified date: 19/9/2021
  Acknowledgement:
  https://www.youtube.com/watch?v=9XJicRt_FaI&t=5536s
  https://youtu.be/f06uUtkmtDE
  https://youtu.be/o-lAsVuskKI
  https://www.tutorialspoint.com/java/index.htm
  http://tutorials.jenkov.com/javafx/index.html
*/

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Main extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/LoadingScreen.fxml"));
        Parent homePage;

        try {
            homePage = loader.load();
        }catch (Exception ex) {
            loader = new FXMLLoader(getClass().getResource("pageFXML/NewPage.fxml"));
            homePage = null;
            try {
                homePage = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // set content for the homepage
            NewController newController = loader.getController();
            newController.setTitle();
            newController.setDescription();
            newController.setImgList();
            newController.setTime();
            newController.setOutlet();
        }


//        // show the stage
        primaryStage.setTitle("The ; expected");
        primaryStage.setScene(new Scene(homePage));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

        // send alert to user if they want to close the application
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            closeApp(primaryStage);
        });

    }

    // open alert scene
    public void closeApp(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close App");
        alert.setContentText("Do you want to close the app?");
        alert.setHeaderText("You are trying to close the app");

        // close the application if the user want to
        if(alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }
}