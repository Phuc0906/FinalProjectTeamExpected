package sample;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sample.BaseController.SportBaseController;
import sample.NewsClass.SupportedMethod;
import sample.NewsObject.NewsManagement;

import java.io.IOException;
import java.util.ArrayList;

public class SportController extends SportBaseController {
    private NewsManagement newsList;

    public void loadExistWeb() throws IOException {
        this.newsList = super.loadSportWeb("https://vnexpress.net/the-thao", "https://nhandan.vn/thethao", "https://thanhnien.vn/the-thao/", "https://vnexpress.net/the-thao");
    }

    public void loadExistWeb(NewsManagement newsList) {
            this.newsList = super.loadSportWeb(newsList);
    }

    @FXML
    ArrayList<ImageView> imgList;
    public void setImgList() {
        for (int i = 0; i < imgList.size(); i++)
            imgList.get(i).setImage(new Image(newsList.getNews(i).getImageURL()));
    }

    @FXML
    ArrayList<Label> titleList;
    public void setTitle() {
        for (int i = 0; i < titleList.size(); i++) {
            titleList.get(i).setFont(Font.font("Time New Roman", FontWeight.BOLD, 30));
            titleList.get(i).setAlignment(Pos.TOP_LEFT);
            titleList.get(i).setText(newsList.getNews(i).getTitle());
        }
    }

    @FXML
    ArrayList<Label> descriptionList;
    public void setDescription() {
        for (int i = 0; i < descriptionList.size(); i++) {
            descriptionList.get(i).setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));
            descriptionList.get(i).setAlignment(Pos.TOP_LEFT);
            descriptionList.get(i).setText(new SupportedMethod().breakingString(newsList.getNews(i).getDescription(), 15));
        }
    }

    public NewsManagement getNewsList() { return this.newsList; }

}
