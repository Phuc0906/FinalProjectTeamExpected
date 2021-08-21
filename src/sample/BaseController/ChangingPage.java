package sample.BaseController;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sample.NewsObject.NewsManagement;
import sample.SupportClass.SupportedMethod;

import java.io.IOException;
import java.util.ArrayList;

public class ChangingPage extends ChangingCategory{

    private SupportedMethod supportedMethod = new SupportedMethod();
    private NewsManagement newsList;


    public ChangingPage(String vnExpressURL1, String vnExpressURL2, String vnExpressURL3,
                        String nhanDanUrl1, String nhanDanUrl2, String nhanDanUrl3, String nhanDanUrl4, String nhanDanUrl5,
                        String tuoiTreURL1, String tuoiTreURL2, String tuoiTreURL3, String tuoiTreURL4,
                        String thanhNienURL1, String thanhNienURL2,
                        String zingURL1, String zingURL2, String zingURL3) throws IOException {
        newsList = new NewsManagement();

        newsList.loadVnExpress(vnExpressURL1);
        newsList.loadVnExpress(vnExpressURL2);
        newsList.loadVnExpress(vnExpressURL3);

        newsList.loadNhanDan(nhanDanUrl1);
        newsList.loadNhanDan(nhanDanUrl2);
        newsList.loadNhanDan(nhanDanUrl3);
        newsList.loadNhanDan(nhanDanUrl4);
        newsList.loadNhanDan(nhanDanUrl5);

        newsList.loadTuoiTre(tuoiTreURL1);
        newsList.loadTuoiTre(tuoiTreURL2);
        newsList.loadTuoiTre(tuoiTreURL3);
        newsList.loadTuoiTre(tuoiTreURL4);

        newsList.loadThanhNien(thanhNienURL1);
        newsList.loadThanhNien(thanhNienURL2);

        newsList.loadZingNews(zingURL1);
        newsList.loadZingNews(zingURL2);
        newsList.loadZingNews(zingURL3);
    }

    public ChangingPage(String vnExpressURL, String nhanDanUrl, String tuoiTreURL, String thanhNienURL, String zingURL) throws IOException {
        newsList = new NewsManagement();
        newsList.loadVnExpress(vnExpressURL);
        newsList.loadTuoiTre(tuoiTreURL);
        newsList.loadThanhNien(thanhNienURL);
        newsList.loadNhanDan(nhanDanUrl);
        newsList.loadZingNews(zingURL);
    }

    public ChangingPage(String vnExpressURL, String nhanDanUrl, String tuoiTreURL) throws IOException {
        newsList = new NewsManagement();
        newsList.loadVnExpress(vnExpressURL);
        newsList.loadNhanDan(nhanDanUrl);
        newsList.loadTuoiTre(tuoiTreURL);
    }

    public void setCovidThanhNien(String thanhNienURL) throws  IOException{
        newsList.covidThanhNien(thanhNienURL);
    }

    @FXML
    AnchorPane coverPane;

    @FXML
    ArrayList<VBox> coverBoxList;

    @FXML
    ScrollPane scrPane;


    @FXML
    ArrayList<ImageView> imgList;

    @FXML
    ArrayList<Label> titleList;

    @FXML
    ArrayList<Label> descriptionList;

    public void setImgList() {
        new SupportedMethod().setImgList(imgList, 0, this.newsList, coverPane);
    }
    public void setImgList2() { supportedMethod.setImgList(imgList, 10, this.newsList, coverPane); }
    public void setImgList3() { supportedMethod.setImgList(imgList, 20, this.newsList, coverPane); }
    public void setImgList4() { supportedMethod.setImgList(imgList, 30, this.newsList, coverPane); }
    public void setImgList5() { supportedMethod.setImgList(imgList, 40, this.newsList, coverPane); }

    public void setDescription() {
        supportedMethod.setDescriptionList(descriptionList, 0, this.newsList, imgList, coverPane);
    }
    public void setDescription2() {
        supportedMethod.setDescriptionList(descriptionList, 10, this.newsList, imgList, coverPane);
    }
    public void setDescription3() {
        supportedMethod.setDescriptionList(descriptionList, 20, this.newsList, imgList, coverPane);
    }
    public void setDescription4() {
        supportedMethod.setDescriptionList(descriptionList, 30, this.newsList, imgList, coverPane);
    }
    public void setDescription5() {
        supportedMethod.setDescriptionList(descriptionList, 40, this.newsList, imgList, coverPane);
    }


    public void setTitle() {
        new SupportedMethod().setTitleList(titleList, 0, this.newsList, imgList, coverPane);
    }
    public void setTitle2() {
        supportedMethod.setTitleList(titleList, 10, this.newsList, imgList, coverPane);
    }
    public void setTitle3() { supportedMethod.setTitleList(titleList, 20, this.newsList, imgList, coverPane); }
    public void setTitle4() {
        supportedMethod.setTitleList(titleList, 30, this.newsList, imgList, coverPane);
    }
    public void setTitle5() {
        supportedMethod.setTitleList(titleList, 40, this.newsList, imgList, coverPane);
    }

    public void moveToPage1(ActionEvent event) throws IOException {
        setImgList();
        setDescription();
        setTitle();
        scrPane.setVvalue(0);
    }


    public void moveToPage2(ActionEvent event) throws IOException {
        setImgList2();
        setDescription2();
        setTitle2();
        scrPane.setVvalue(0);
    }

    public void moveToPage3(ActionEvent event) throws IOException {

        setImgList3();
        setDescription3();
        setTitle3();
        scrPane.setVvalue(0);
    }

    public void moveToPage4(ActionEvent event) throws IOException {

        setImgList4();
        setDescription4();
        setTitle4();
        scrPane.setVvalue(0);
    }

    public void moveToPage5(ActionEvent event) throws IOException {

        setImgList5();
        setDescription5();
        setTitle5();
        scrPane.setVvalue(0);
    }

    public void bindingPane() {
        for (VBox coverBox : coverBoxList) {
            coverBox.prefWidthProperty().bind(coverPane.widthProperty().subtract(imgList.get(0).fitWidthProperty()));
        }
    }
}
