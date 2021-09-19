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

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadingScreenController implements Initializable {
    @FXML
    AnchorPane coverPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new SplashScreen().start();
    }

    class SplashScreen extends Thread {
        @Override
        public void run() {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/NewPage.fxml"));
            Parent homePage = null;
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
            Parent finalHomePage = homePage;
            Platform.runLater(() -> {

                Stage stage = new Stage();
                stage.setScene(new Scene(finalHomePage));
                stage.show();

                coverPane.getScene().getWindow().hide();
            });


        }
    }
}
