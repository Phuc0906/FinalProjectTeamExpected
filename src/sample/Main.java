package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import sample.SupportClass.ScrapeArticle;
import sample.SupportClass.SupportedMethod;

public class Main extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/NewPage.fxml"));
        Parent homePage = loader.load();

        NewController newController = loader.getController();
        newController.setTitle();
        newController.setDescription();
        newController.setImgList();
        newController.setTime();



        primaryStage.setTitle("Projekt Red");
        primaryStage.setScene(new Scene(homePage));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            closeApp(primaryStage);
        });

    }
}