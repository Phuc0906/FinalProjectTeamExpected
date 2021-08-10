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
import sample.BaseController.ChangingPage;
import sample.SupportClass.SupportedMethod;
import sample.NewsObject.NewsManagement;

import java.io.IOException;
import java.util.ArrayList;

public class WorldController extends ChangingPage {

    public WorldController() throws IOException {
        super("https://vnexpress.net/the-gioi", "https://nhandan.vn/thegioi", "https://tuoitre.vn/the-gioi.htm", "https://thanhnien.vn/the-gioi/");
    }
}
