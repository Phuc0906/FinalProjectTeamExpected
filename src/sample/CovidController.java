package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sample.NewsClass.BriefNews;
import sample.NewsClass.SupportedMethod;
import sample.NewsClass.VnExpressCateURL;
import sample.NewsClass.WorldNews;

import java.io.IOException;

public class CovidController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private WorldNews worldNews;

    public CovidController() throws IOException {
        VnExpressCateURL vnExpressCateURL = new VnExpressCateURL();
        this.worldNews = new WorldNews();
        worldNews.extractNewsFromWeb(vnExpressCateURL.getWorldURL());
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
        root = FXMLLoader.load(getClass().getResource("pageFXML/CovidPage.fxml"));
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

    public void toSportsPage(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("pageFXML/SportsPage.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toEntertainmentPage(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("pageFXML/EntertainmentPage.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

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

    @FXML
    ImageView covidImage;
    public void setCovidImage() throws IOException {
        Image image = new Image(worldNews.getImageURL());
        covidImage.setImage(image);
    }

    @FXML
    Label myText;
    public void setTitle1() {
        myText.setFont(Font.font("Time New Roman", FontWeight.BOLD, 30));
        myText.setAlignment(Pos.TOP_LEFT);
        myText.setText(worldNews.getTitle());

    }

    @FXML
    Label description1;
    public void setDescription1() {
        description1.setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));
        description1.setAlignment(Pos.TOP_LEFT);
        description1.setText(new SupportedMethod().breakingString(worldNews.getDescription(), 50));
    }
}
