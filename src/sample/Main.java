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
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sample.NewsObject.NewsManagement;
import sample.SupportClass.SupportedMethod;

public class Main extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception {
        Parent homePage = FXMLLoader.load(getClass().getResource("pageFXML/NewPage.fxml"));
        primaryStage.setTitle("Projekt Red");
        primaryStage.setScene(new Scene(homePage, 1000, 720));
        new SupportedMethod().loadNhandan();
        primaryStage.show();
    }
}