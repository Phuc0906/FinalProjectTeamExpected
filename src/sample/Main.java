/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 2021B
  Assessment: Final Project
  Created  date: 17/7/2021
  Author: Nguyen Hung Anh s3877798
    Hoang Phuc s3879362
    Le Tan Phong s3877819
    Thai Thuan s3877024
  Last modified date: 11/9/2021
  Acknowledgement: Thanks and give credits to the resources that you used in this file
*/
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

import java.io.FileNotFoundException;
import java.io.PrintWriter;

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
        newController.setOutlet();

        primaryStage.setTitle("The ; expected");
        primaryStage.setScene(new Scene(homePage));
        primaryStage.show();


        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            try {
                clearFile();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            closeApp(primaryStage);
        });

    }

    public void clearFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("src/sample/Document/categoryLinks.txt");
        writer.print("");
        writer.close();
        PrintWriter writer1 = new PrintWriter("src/sample/Document/covid.txt");
        writer1.print("");
        writer1.close();
        PrintWriter writer2 = new PrintWriter("src/sample/Document/nhanDancategoryLinks.txt");
        writer2.print("");
        writer2.close();
        PrintWriter writer3 = new PrintWriter("src/sample/Document/thanhNiencategoryLinks.txt");
        writer3.print("");
        writer3.close();
        PrintWriter writer4 = new PrintWriter("src/sample/Document/tuoiTrecategoryLinks.txt");
        writer4.print("");
        writer4.close();
        PrintWriter writer5 = new PrintWriter("src/sample/Document/vnExpresscategoryLinks.txt");
        writer5.print("");
        writer5.close();
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