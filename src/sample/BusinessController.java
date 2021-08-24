package sample;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sample.BaseController.ChangingPage;
import sample.NewsObject.NewsManagement;
import sample.SupportClass.SupportedMethod;

import java.io.IOException;
import java.util.ArrayList;

public class BusinessController extends ChangingPage {
    public BusinessController() throws IOException {
        super("https://vnexpress.net/kinh-doanh", "https://nhandan.vn/kinhte", "https://tuoitre.vn/kinh-doanh.htm", "https://thanhnien.vn/tai-chinh-kinh-doanh/doanh-nghiep/", "https://zingnews.vn/kinh-doanh-tai-chinh.html");
    }


}
