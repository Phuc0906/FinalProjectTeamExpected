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

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sample.NewsObject.News;

import javax.print.Doc;


public class Main extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception{
        Parent homePage = FXMLLoader.load(getClass().getResource("pageFXML/HomePage.fxml"));
        primaryStage.setTitle("Projekt Red");
        primaryStage.setScene(new Scene(homePage,1000, 720));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> closeApp(primaryStage));


//        Document doc = Jsoup.connect("https://nhandan.vn/thegioi").get();
//        Elements ele = doc.select("div.box-widget-loaded");
//        Elements element = ele.select("article");
//        for (Element element1 : element) {
//            System.out.println(element1.select("div.box-title a").attr("title"));
//            System.out.println("https://nhandan.vn/" + element1.select("div.box-title a").attr("href"));
//            System.out.println(element1.select("div.box-img a img").attr("data-src"));
//            System.out.println(element1.select("div.box-des").text());
//        }
//        System.out.println(element.size());

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