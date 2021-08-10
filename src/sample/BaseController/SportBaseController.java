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

public class SportBaseController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private NewsManagement newsList;

    public NewsManagement loadSportWeb(String tuoiTreURL, String nhanDanURL, String thanhNienURL, String vnExpressURL) throws IOException {
        newsList = new NewsManagement();
        newsList.loadVnExpress("https://vnexpress.net/the-thao");
        newsList.loadTuoiTre("https://tuoitre.vn/the-thao.htm");
        newsList.loadThanhNien("https://thanhnien.vn/the-thao/");
        newsList.loadNhanDan( "https://nhandan.vn/thethao");

        return newsList;
    }

    public NewsManagement loadSportWeb(NewsManagement newsList) {
        return newsList;
    }



    public void toNewPage(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("pageFXML/NewPage.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toHomePage(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("pageFXML/HomePage.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toCovidPage(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/CovidPage.fxml"));
//        root = FXMLLoader.load(getClass().getResource("pageFXML/CovidPage.fxml"));
        root = loader.load();
        CovidController covidController = loader.getController();
        covidController.setCovidImage();
        covidController.setTitle();
        covidController.setDescription();

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toEntertainmentPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/EntertainmentFXML/EntertainmentPage.fxml"));
        root = loader.load();

        EntertainmentController entertainmentController = loader.getController();
        entertainmentController.loadExistWeb();
        entertainmentController.setDescription();
        entertainmentController.setTitle();
        entertainmentController.setImgList();

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toPoliticsPage(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("pageFXML/PoliticsPage.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toBusinessPage(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("pageFXML/BusinessPage.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toTechPage(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("pageFXML/TechPage.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toHealthPage(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("pageFXML/HealthPage.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

//    public void toSportsPage(ActionEvent actionEvent) throws IOException {
//        root = FXMLLoader.load(getClass().getResource("pageFXML/SportsPage.fxml"));
//        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }


    public void toWorldPage(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("pageFXML/WorldPage.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toOthersPage(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("pageFXML/OthersPage.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
        entertainmentController.loadWeb(this.newsList);
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
        entertainmentController.loadWeb(this.newsList);
        entertainmentController.setDescription();
        entertainmentController.setTitle();
        entertainmentController.setImgList();

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void moveToPage1(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/EntertainmentFXML/EntertainmentPage1.fxml"));
        root = loader.load();

        EntertainmentController entertainmentController = loader.getController();
        entertainmentController.loadWeb(this.newsList);
        entertainmentController.setDescription();
        entertainmentController.setTitle();
        entertainmentController.setImgList();

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
