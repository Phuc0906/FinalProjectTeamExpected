package sample.BaseController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.*;

import java.io.IOException;

public class ChangingCategory {
    private Stage stage;
    private Scene scene;
    private Parent root;

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

    public void toPoliticsPage(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("pageFXML/PoliticsPage.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toBusinessPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/BusinessPage.fxml"));
        root = loader.load();
        BusinessController businessController = loader.getController();
        businessController.setImgList();
        businessController.setTitle();
        businessController.setDescription();

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/HealthPage.fxml"));
        root = loader.load();
        HealthController healthController = loader.getController();
        healthController.setImgList();
        healthController.setTitle();
        healthController.setDescription();

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toSportsPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/SportsPage.fxml"));
        root = loader.load();
        SportController sportController = loader.getController();
        sportController.setImgList();
        sportController.setTitle();
        sportController.setDescription();

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toEntertainmentPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/EntertainmentPage.fxml"));
        root = loader.load();

        EntertainmentController entertainmentController = loader.getController();
        entertainmentController.setTitle();
        entertainmentController.setDescription();
        entertainmentController.setImgList();

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toWorldPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/WorldPage.fxml"));
        root = loader.load();
        WorldController worldController = loader.getController();
        worldController.setImgList();
        worldController.setTitle();
        worldController.setDescription();

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
}
