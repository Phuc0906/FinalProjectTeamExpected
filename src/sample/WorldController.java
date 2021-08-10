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

public class WorldController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private NewsManagement worldNews;

    public WorldController() throws IOException {
        this.worldNews = new NewsManagement();
//        this.worldNews.loadVnExpress("https://vnexpress.net/the-gioi");
        this.worldNews.loadTuoiTre("https://tuoitre.vn/the-gioi.htm");
        this.worldNews.loadThanhNien("https://thanhnien.vn/the-gioi/");
        this.worldNews.loadNhanDan("https://nhandan.vn/thegioi");
        this.worldNews.loadNhanDan("https://nhandan.vn/xahoi");
        this.worldNews.loadNhanDan("https://nhandan.vn/phapluat");
        this.worldNews.loadNhanDan("https://nhandan.vn/thethao");
    }

//  To Category methods block
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/WorldPage.fxml"));
        root = loader.load();
        WorldController worldController = loader.getController();
        worldController.setImage();
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

//    Set Img methods block
    @FXML
    ArrayList<ImageView> imgList;
    public void setImage() {
        for (int i = 0; i < imgList.size(); i++)
            imgList.get(i).setImage(new Image(worldNews.getNews(i).getImageURL()));
    }
    
    public void setImage2() {
        for (int i = 0; i < imgList.size(); i++)
            imgList.get(i).setImage(new Image(worldNews.getNews(10 + i).getImageURL()));
    }

    public void setImage3() {
        for (int i = 0; i < imgList.size(); i++)
            imgList.get(i).setImage(new Image(worldNews.getNews(20 + i).getImageURL()));
    }

    public void setImage4() {
        for (int i = 0; i < imgList.size(); i++)
            imgList.get(i).setImage(new Image(worldNews.getNews(30 + i).getImageURL()));
    }

    public void setImage5() {
        for (int i = 0; i < imgList.size(); i++)
            imgList.get(i).setImage(new Image(worldNews.getNews(40 + i).getImageURL()));
    }

//    Set title methods block
    @FXML
    ArrayList<Label> titleList;
    public void setTitle() {
        for (int i = 0; i < titleList.size(); i++) {
            titleList.get(i).setFont(Font.font("Time New Roman", FontWeight.BOLD, 30));
            titleList.get(i).setAlignment(Pos.TOP_LEFT);
            titleList.get(i).setText(worldNews.getNews(i).getTitle());
        }
    }

    public void setTitle2() {
        for (int i = 0; i < titleList.size(); i++) {
            titleList.get(i).setFont(Font.font("Time New Roman", FontWeight.BOLD, 30));
            titleList.get(i).setAlignment(Pos.TOP_LEFT);
            titleList.get(i).setText(worldNews.getNews(10 + i).getTitle());
        }
    }

    public void setTitle3() {
        for (int i = 0; i < titleList.size(); i++) {
            titleList.get(i).setFont(Font.font("Time New Roman", FontWeight.BOLD, 30));
            titleList.get(i).setAlignment(Pos.TOP_LEFT);
            titleList.get(i).setText(worldNews.getNews(20 + i).getTitle());
        }
    }

    public void setTitle4() {
        for (int i = 0; i < titleList.size(); i++) {
            titleList.get(i).setFont(Font.font("Time New Roman", FontWeight.BOLD, 30));
            titleList.get(i).setAlignment(Pos.TOP_LEFT);
            titleList.get(i).setText(worldNews.getNews(30 + i).getTitle());
        }
    }

    public void setTitle5() {
        for (int i = 0; i < titleList.size(); i++) {
            titleList.get(i).setFont(Font.font("Time New Roman", FontWeight.BOLD, 30));
            titleList.get(i).setAlignment(Pos.TOP_LEFT);
            titleList.get(i).setText(worldNews.getNews(40 + i).getTitle());
        }
    }

//    Set description methods block

    @FXML
    ArrayList<Label> descriptionList;
    public void setDescription() {
        for (int i = 0; i < descriptionList.size(); i++) {
            descriptionList.get(i).setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));
            descriptionList.get(i).setAlignment(Pos.TOP_LEFT);
            descriptionList.get(i).setText(new SupportedMethod().breakingString(worldNews.getNews(i).getDescription(), 15));
        }
    }

    public void setDescription2() {
        for (int i = 0; i < descriptionList.size(); i++) {
            descriptionList.get(i).setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));
            descriptionList.get(i).setAlignment(Pos.TOP_LEFT);
            descriptionList.get(i).setText(new SupportedMethod().breakingString(worldNews.getNews(10 + i).getDescription(), 15));
        }
    }

    public void setDescription3() {
        for (int i = 0; i < descriptionList.size(); i++) {
            descriptionList.get(i).setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));
            descriptionList.get(i).setAlignment(Pos.TOP_LEFT);
            descriptionList.get(i).setText(new SupportedMethod().breakingString(worldNews.getNews(20 + i).getDescription(), 15));
        }
    }

    public void setDescription4() {
        for (int i = 0; i < descriptionList.size(); i++) {
            descriptionList.get(i).setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));
            descriptionList.get(i).setAlignment(Pos.TOP_LEFT);
            descriptionList.get(i).setText(new SupportedMethod().breakingString(worldNews.getNews(30 + i).getDescription(), 15));
        }
    }

    public void setDescription5() {
        for (int i = 0; i < descriptionList.size(); i++) {
            descriptionList.get(i).setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));
            descriptionList.get(i).setAlignment(Pos.TOP_LEFT);
            descriptionList.get(i).setText(new SupportedMethod().breakingString(worldNews.getNews(40 + i).getDescription(), 15));
        }
    }

//   ToPage methods block
    public void toWorldPage2(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/WorldPage.fxml"));
        root = loader.load();
        WorldController worldController = loader.getController();
        worldController.setImage2();
        worldController.setTitle2();
        worldController.setDescription2();

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toWorldPage3(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/WorldPage.fxml"));
        root = loader.load();
        WorldController worldController = loader.getController();
        worldController.setImage3();
        worldController.setTitle3();
        worldController.setDescription3();

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toWorldPage4(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/WorldPage.fxml"));
        root = loader.load();
        WorldController worldController = loader.getController();
        worldController.setImage4();
        worldController.setTitle4();
        worldController.setDescription4();

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toWorldPage5(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/WorldPage.fxml"));
        root = loader.load();
        WorldController worldController = loader.getController();
        worldController.setImage5();
        worldController.setTitle5();
        worldController.setDescription5();

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
