package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import sample.NewsObject.AppendingArticle;
import sample.NewsObject.ScrapeArticle;
import sample.SupportClass.SupportedMethod;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/NewPage.fxml"));
        Parent homePage = loader.load();

        //article list
        //https://tuoitre.vn/
        //https://zingnews.vn/
        //https://nhandan.vn/
        //https://vnexpress.net/
        //https://thanhnien.vn

        //article: https://nhandan.vn/chuyen-lam-an/giua-thang-11-nha-may-duong-phung-hiep-se-vao-vu-san-xuat-663008/

        try {
            AppendingArticle appendingArticle = new AppendingArticle();
        }catch (Exception ex) {
            ex.printStackTrace();
        }

        new SupportedMethod().scrapeArticle("https://thanhnien.vn/the-thao/bong-da-viet-nam/chu-tich-nuoc-nguyen-xuan-phuc-gui-loi-dong-vien-toi-thay-tro-hlv-park-hang-seo-139700t.html");

        primaryStage.setTitle("Projekt Red");
        primaryStage.setScene(new Scene(homePage, 1000, 720));
//        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> closeApp(primaryStage));
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