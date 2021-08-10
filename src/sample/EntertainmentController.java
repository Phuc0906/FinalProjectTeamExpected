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
import sample.BaseController.EntertainmentBaseController;
import sample.NewsClass.SupportedMethod;
import sample.NewsObject.NewsManagement;

import java.io.IOException;
import java.util.ArrayList;

public class EntertainmentController extends EntertainmentBaseController {

    private NewsManagement newsList;

    public void loadExistWeb() throws IOException {
        this.newsList = super.loadWeb("https://tuoitre.vn/giai-tri.htm", "https://nhandan.vn/thegioi", "https://thanhnien.vn/giai-tri/", "https://vnexpress.net/giai-tri");

    }

    public void loadExistWeb(NewsManagement newsList) {
        this.newsList = super.loadWeb(newsList);
    }

    @FXML
    ArrayList<ImageView> imgList;
    public void setImgList() {
        new SupportedMethod().setImgList(imgList, 0, this.newsList);

    }

    @FXML
    ArrayList<Label> titleList;
    public void setTitle() {
        new SupportedMethod().setTitleList(titleList, 0, this.newsList);
    }

    @FXML
    ArrayList<Label> descriptionList;
    public void setDescription() {
        new SupportedMethod().setDescriptionList(descriptionList, 0, this.newsList);
    }

}
