package sample;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sample.BaseController.ChangingPage;

import java.io.IOException;

public class BusinessController extends ChangingPage {
    public BusinessController() throws IOException {
        super("https://vnexpress.net/kinh-doanh", "https://nhandan.vn/kinhte", "https://tuoitre.vn/kinh-doanh.htm", "https://thanhnien.vn/tai-chinh-kinh-doanh/doanh-nghiep/", "https://zingnews.vn/kinh-doanh-tai-chinh.html");
    }
}
