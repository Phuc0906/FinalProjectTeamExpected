package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import sample.SupportClass.SupportedMethod;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/NewPage(Phong).fxml"));
        Parent homePage = loader.load();

        NewController newController = loader.getController();
        newController.setTitle();
        newController.setDescription();
        newController.setImgList();

        primaryStage.setTitle("Projekt Red");
        primaryStage.setScene(new Scene(homePage, 1000, 720));
//        new SupportedMethod().ThanhNienArtical("https://thanhnien.vn/van-hoa/gia-dinh-cam-van-hat-sai-gon-toi-se-gui-uoc-mong-thanh-pho-se-lai-vui-1445009.html");
        primaryStage.show();

    }
}