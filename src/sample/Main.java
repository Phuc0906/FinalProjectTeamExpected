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
import sample.SupportClass.SupportedMethod;

public class Main extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception{
        Parent homePage = FXMLLoader.load(getClass().getResource("pageFXML/HomePage.fxml"));
        primaryStage.setTitle("Projekt Red");
        primaryStage.setScene(new Scene(homePage,1000, 720));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> closeApp(primaryStage));
//        new SupportedMethod().scrapeArticle("https://timkiem.vnexpress.net/?q=covid");

        String newsURL;
        String title;
        String description;
        String imageURL;
        Document doc = Jsoup.connect("https://thanhnien.vn/covid-19/").get();
        Elements el = doc.select("div.relative");
        for (Element element : el.select("article")) {
            newsURL = element.select("a").attr("href");
            ///in special cases
            if (!newsURL.contains("https")) newsURL = "https://thanhnien.vn" + element.select("a").attr("href");
            title = element.select("a").attr("title");
            imageURL = element.select("a img").attr("data-src");
            description = element.select("div.summary").text();
            //in special cases
            if (description==null) description = element.select("div.summary div").text();
            System.out.println(newsURL);
            System.out.println(title);
            System.out.println(imageURL );
            System.out.println(description);
        }
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