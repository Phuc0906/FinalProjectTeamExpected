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
import sample.SupportClass.SupportedMethod;

public class Main extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception {
        Parent homePage = FXMLLoader.load(getClass().getResource("pageFXML/Article.fxml"));
        primaryStage.setTitle("Projekt Red");
        primaryStage.setScene(new Scene(homePage, 1000, 720));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> closeApp(primaryStage));

        Document doc = Jsoup.connect("https://tuoitre.vn/tui-qua-an-sinh-duoc-trao-tan-tay-nguoi-dan-tp-hcm-ngay-gian-cach-20210823130245963.htm").get();
        Elements paragraph = doc.select("div.main-content-body p");
//        System.out.println(paragraph);
        String[] paragraphString = paragraph.toString().split("\n");
        for (String para: paragraphString) {
            Document docScript = Jsoup.parse(para);
            if(!docScript.text().contains("Ảnh") && !docScript.text().contains("TTO")) {
                System.out.println(docScript.text());
            }
        }
        Elements author = doc.select("div.main-content-body div.author");
        System.out.println(author.text());
//        new SupportedMethod().setArticle("https://vnexpress.net/the-kho-trong-dai-dich-cua-nganh-ban-le-4344766.html");
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