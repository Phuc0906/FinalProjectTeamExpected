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
            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    Stage stage = new Stage();
                    stage.setScene(new Scene(finalHomePage));
                    stage.show();

                    coverPane.getScene().getWindow().hide();
                }
            });


        }
    }
}
