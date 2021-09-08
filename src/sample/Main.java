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
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends Application {
    private static SupportedMethod supportedMethod = new SupportedMethod();

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

        supportedMethod.setFile();

        supportedMethod.scrapeCategory("https://vnexpress.net");
        supportedMethod.scrapeCategory("https://zingnews.vn");
        supportedMethod.scrapeCategory("https://nhandan.vn");
        supportedMethod.scrapeCategory("https://tuoitre.vn");
        supportedMethod.scrapeCategory("https://thanhnien.vn");
        System.out.println("Done scraping category");
        supportedMethod.closeFile();

        supportedMethod.setReadCategory();
        supportedMethod.setFileArticle();
        System.out.println("Start scraping");
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 1; i < 9; i++) {
            executorService.execute(new ThreadScraping(i));
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {

        }

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

    private class ThreadScraping implements Runnable {
        int whichArticle;

        public ThreadScraping(int whichArticle) {
            this.whichArticle = whichArticle;
        }

        @Override
        public void run() {
            try {
                supportedMethod.scrapeArticleLink(whichArticle);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}