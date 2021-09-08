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
import sample.BaseController.ChangingPage;
import sample.SupportClass.SupportedMethod;
import sample.NewsObject.NewsManagement;

import java.io.IOException;
import java.util.ArrayList;

public class CovidController extends ChangingPage {
    public CovidController() throws IOException {
        super("https://vnexpress.net", "https://nhandan.vn", "https://tuoitre.vn", "https://thanhnien.vn", "https://zingnews.vn");
    }
}
