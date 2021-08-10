package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sample.BaseController.ChangingCategory;
import sample.SupportClass.SupportedMethod;
import sample.NewsObject.NewsManagement;

import java.io.IOException;
import java.util.ArrayList;

public class WorldController extends ChangingCategory {

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
    @FXML
    ScrollPane worldScrPane;
    public void toWorldPage2(ActionEvent actionEvent) throws IOException {
        setImage2();
        setTitle2();
        setDescription2();
        worldScrPane.setVvalue(0);
    }

    public void toWorldPage3(ActionEvent actionEvent) throws IOException {
        setImage3();
        setTitle3();
        setDescription3();
        worldScrPane.setVvalue(0);
    }

    public void toWorldPage4(ActionEvent actionEvent) throws IOException {
        setImage4();
        setTitle4();
        setDescription4();
        worldScrPane.setVvalue(0);
    }

    public void toWorldPage5(ActionEvent actionEvent) throws IOException {
        setImage5();
        setTitle5();
        setDescription5();
        worldScrPane.setVvalue(0);
    }
}
