package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.event.AncestorEvent;
import java.io.IOException;

public class PageController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Button New, covid, politics, business, health, entertainment, sports, world, others, tech;
    public void toNewPage(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("NewPage.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void toHomePage(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void toCovidPage(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CovidPage.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void toPoliticsPage(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("PoliticsPage.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void toBusinessPage(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("BusinessPage.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void toTechPage(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TechPage.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void toHealthPage(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("HealthPage.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void toSportsPage(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SportsPage.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void toEntertainmentPage(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("EntertainmentPage.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void toWorldPage(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("WorldPage.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void toOthersPage(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("OthersPage.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
