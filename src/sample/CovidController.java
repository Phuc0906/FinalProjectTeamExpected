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
import sample.NewsClass.SupportedMethod;
import sample.NewsObject.NewsManagement;

import java.io.IOException;
import java.util.ArrayList;

public class CovidController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private NewsManagement worldNews;

    public CovidController() throws IOException {
        this.worldNews = new NewsManagement();
        this.worldNews.loadVnExpress("https://vnexpress.net/the-gioi");
        this.worldNews.loadTuoiTre("https://tuoitre.vn/giai-tri.htm");
        this.worldNews.loadThanhNien("https://thanhnien.vn/the-thao/");
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
    ImageView covidImage, covidImage2, covidImage3, covidImage4;
    public void setCovidImage() throws IOException {
        covidImage.setImage(new Image(worldNews.getNews(0).getImageURL()));
        covidImage2.setImage(new Image(worldNews.getNews(1).getImageURL()));
        covidImage3.setImage(new Image(worldNews.getNews(2).getImageURL()));
        covidImage4.setImage(new Image(worldNews.getNews(3).getImageURL()));
    }

    @FXML
    Label title1, title2, title3, title4;
    public void setTitle() {
        title1.setFont(Font.font("Time New Roman", FontWeight.BOLD, 30));
        title1.setAlignment(Pos.TOP_LEFT);
        title1.setText(worldNews.getNews(0).getTitle());

        title2.setFont(Font.font("Time New Roman", FontWeight.BOLD, 30));
        title2.setAlignment(Pos.TOP_LEFT);
        title2.setText(worldNews.getNews(1).getTitle());

        title3.setFont(Font.font("Time New Roman", FontWeight.BOLD, 30));
        title3.setAlignment(Pos.TOP_LEFT);
        title3.setText(worldNews.getNews(2).getTitle());

        title4.setFont(Font.font("Time New Roman", FontWeight.BOLD, 30));
        title4.setAlignment(Pos.TOP_LEFT);
        title4.setText(worldNews.getNews(3).getTitle());

    }

    @FXML
    ArrayList<Label> descriptionList;
    public void setDescription() {
        for (int i = 0; i < descriptionList.size(); i++) {
            descriptionList.get(i).setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));
            descriptionList.get(i).setAlignment(Pos.TOP_LEFT);
            descriptionList.get(i).setText(new SupportedMethod().breakingString(worldNews.getNews(i).getDescription(), 15));
        }
//        description1.setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));
//        description1.setAlignment(Pos.TOP_LEFT);
//        description1.setText(new SupportedMethod().breakingString(worldNews.getNews(0).getDescription(), 15));
//
//        description2.setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));
//        description2.setAlignment(Pos.TOP_LEFT);
//        description2.setText(new SupportedMethod().breakingString(worldNews.getNews(1).getDescription(), 15));
//
//        description3.setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));
//        description3.setAlignment(Pos.TOP_LEFT);
//        description3.setText(new SupportedMethod().breakingString(worldNews.getNews(2).getDescription(), 15));
//
//        description4.setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));
//        description4.setAlignment(Pos.TOP_LEFT);
//        description4.setText(new SupportedMethod().breakingString(worldNews.getNews(3).getDescription(), 15));
    }

}
