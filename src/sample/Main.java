package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import sample.SupportClass.SupportedMethod;

public class Main extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/NewPage.fxml"));
        Parent homePage = loader.load();

        ArticleController newController = loader.getController();
        newController.setContent();
        new SupportedMethod().loadZingArticle("https://zingnews.vn/thu-tuong-lam-truong-ban-chi-dao-quoc-gia-phong-chong-dich-covid-19-post1254703.html");
        primaryStage.setTitle("Projekt Red");
        primaryStage.setScene(new Scene(homePage, 1000, 720));
        primaryStage.show();
    }
}