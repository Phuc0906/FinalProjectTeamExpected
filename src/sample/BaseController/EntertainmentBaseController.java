package sample.BaseController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.*;
import sample.NewsObject.NewsManagement;

import java.io.IOException;

public class EntertainmentBaseController extends ChangingCategory{
    private Stage stage;
    private Scene scene;
    private Parent root;

    private NewsManagement newsList;

    public NewsManagement loadWeb(String tuoiTreURL, String nhanDanURL, String thanhNienURL, String vnExpressURL) throws IOException {
        newsList = new NewsManagement();
        newsList.loadVnExpress(vnExpressURL);
        newsList.loadTuoiTre(tuoiTreURL);
        newsList.loadThanhNien(thanhNienURL);
        newsList.loadNhanDan( nhanDanURL);
        System.out.println("DONE SCRAPING");

        return newsList;
    }

    public NewsManagement loadWeb(NewsManagement newsList) {
        this.newsList = newsList;
        return newsList;
    }


    public void moveToPage2(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/EntertainmentFXML/EntertainmentPage2.fxml"));
        root = loader.load();

        EntertainmentControllerPage2 entertainmentController = loader.getController();
        entertainmentController.loadExistWeb(this.newsList);
        entertainmentController.setDescription();
        entertainmentController.setTitle();
        entertainmentController.setImgList();

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void moveToPage3(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/EntertainmentFXML/EntertainmentPage3.fxml"));
        root = loader.load();
        EntertainmentControllerPage3 entertainmentController = loader.getController();
        entertainmentController.loadExistWeb(this.newsList);
        entertainmentController.setDescription();
        entertainmentController.setTitle();
        entertainmentController.setImgList();


        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void moveToPage4(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/EntertainmentFXML/EntertainmentPage4.fxml"));
        root = loader.load();

        EntertainmentControllerPage4 entertainmentController = loader.getController();
        entertainmentController.loadExistWeb(this.newsList);
        entertainmentController.setDescription();
        entertainmentController.setTitle();
        entertainmentController.setImgList();

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void moveToPage1(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/EntertainmentFXML/EntertainmentPage.fxml"));
        root = loader.load();

        EntertainmentController entertainmentController = loader.getController();
        entertainmentController.loadExistWeb(this.newsList);
        entertainmentController.setDescription();
        entertainmentController.setTitle();
        entertainmentController.setImgList();

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void moveToPage5(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/EntertainmentFXML/EntertainmentPage5.fxml"));
        root = loader.load();

        EntertainmentControllerPage5 entertainmentController = loader.getController();
        entertainmentController.loadExistWeb(this.newsList);
        entertainmentController.setDescription();
        entertainmentController.setTitle();
        entertainmentController.setImgList();

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
