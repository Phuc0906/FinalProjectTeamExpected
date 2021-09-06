package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import sample.SupportClass.SupportedMethod;

import java.io.File;
import java.util.Scanner;

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

//        try {
//            AppendingArticle appendingArticle = new AppendingArticle();
//        }catch (Exception ex) {
//            ex.printStackTrace();
//        }
        long start = System.currentTimeMillis();
        SupportedMethod supportedMethod = new SupportedMethod();
        supportedMethod.setFile();

        supportedMethod.scrapeCategory("https://vnexpress.net");
        supportedMethod.scrapeCategory("https://zingnews.vn");
        supportedMethod.scrapeCategory("https://nhandan.vn");
        supportedMethod.scrapeCategory("https://tuoitre.vn");
        supportedMethod.closeFile();
        System.out.println("Time consumption: " + (System.currentTimeMillis() - start));

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