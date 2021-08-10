package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.BaseController.ChangingCategory;
import sample.SupportClass.SupportedMethod;
import sample.NewsObject.NewsManagement;

import java.io.IOException;
import java.util.ArrayList;

public class EntertainmentController extends ChangingCategory {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private SupportedMethod supportedMethod = new SupportedMethod();

    private NewsManagement newsList;
    public EntertainmentController() throws IOException {
        newsList = new NewsManagement();
        newsList.loadVnExpress("https://vnexpress.net/giai-tri");
        newsList.loadTuoiTre("https://tuoitre.vn/giai-tri.htm");
        newsList.loadThanhNien("https://thanhnien.vn/giai-tri/");
        newsList.loadNhanDan( "https://nhandan.vn/thegioi");
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
        supportedMethod.setDescriptionList(descriptionList, 0, this.newsList);
    }

    public void setImgList2() { supportedMethod.setImgList(imgList, 10, this.newsList); }

    public void setTitle2() {
        supportedMethod.setTitleList(titleList, 10, this.newsList);
    }

    public void setDescription2() {
        supportedMethod.setDescriptionList(descriptionList, 10, this.newsList);
    }

    public void setImgList3() { supportedMethod.setImgList(imgList, 20, this.newsList); }

    public void setTitle3() { supportedMethod.setTitleList(titleList, 20, this.newsList); }

    public void setDescription3() {
        supportedMethod.setDescriptionList(descriptionList, 20, this.newsList);
    }

    public void setImgList4() { supportedMethod.setImgList(imgList, 30, this.newsList); }

    public void setTitle4() {
        supportedMethod.setTitleList(titleList, 30, this.newsList);
    }

    public void setDescription4() {
        supportedMethod.setDescriptionList(descriptionList, 30, this.newsList);
    }

    public void setImgList5() { supportedMethod.setImgList(imgList, 40, this.newsList); }

    public void setTitle5() {
        supportedMethod.setTitleList(titleList, 40, this.newsList);
    }

    public void setDescription5() {
        supportedMethod.setDescriptionList(descriptionList, 40, this.newsList);
    }


    public void moveToPage2(ActionEvent event) throws IOException {
        setTitle2();
        setImgList2();
        setDescription2();
    }

    public void moveToPage3(ActionEvent event) throws IOException {
        setTitle3();
        setImgList3();
        setDescription3();
    }

    public void moveToPage4(ActionEvent event) throws IOException {
        setTitle4();
        setImgList4();
        setDescription4();
    }

    public void moveToPage1(ActionEvent event) throws IOException {
        setTitle();
        setImgList();
        setDescription();
    }

    public void moveToPage5(ActionEvent event) throws IOException {
        setTitle5();
        setImgList5();
        setDescription5();
    }
}
