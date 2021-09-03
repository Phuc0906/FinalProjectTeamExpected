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

        primaryStage.setTitle("Projekt Red");
        primaryStage.setScene(new Scene(homePage, 1000, 720));
        new SupportedMethod().ThanhNienArtical("https://thanhnien.vn/the-thao/bong-da-viet-nam/vong-loai-world-cup-2022-a-rap-xe-ut-vs-tuyen-viet-nam-khang-dinh-ban-linh-139656t.html");
        primaryStage.show();

    }
}