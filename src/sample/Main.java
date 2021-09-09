package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import sample.SupportClass.ScrapeArticle;
import sample.SupportClass.ScrapingCovid;
import sample.SupportClass.SupportedMethod;

public class Main extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/NewPage.fxml"));
        Parent homePage = loader.load();

        try {
            ScrapingCovid scrapingCovid = new ScrapingCovid();
        }catch (Exception ex) {
            ex.printStackTrace();
        }

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