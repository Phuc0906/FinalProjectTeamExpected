package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class Main extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception{
        Parent homePage = FXMLLoader.load(getClass().getResource("pageFXML/HomePage.fxml"));
        primaryStage.setTitle("Projekt Red");
        primaryStage.setScene(new Scene(homePage,1000, 720));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> closeApp(primaryStage));

        Document doc = Jsoup.connect("https://vnexpress.net/the-gioi").get();
        Elements file = doc.select("div.wrapper-topstory-folder");
        System.out.println(file.select("article.article-topstory" +
                " div.thumb-art" +
                " a").attr("href"));

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