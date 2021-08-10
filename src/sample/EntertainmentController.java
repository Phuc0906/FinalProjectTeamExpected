package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.BaseController.ChangingCategory;
import sample.BaseController.ChangingPage;
import sample.SupportClass.SupportedMethod;
import sample.NewsObject.NewsManagement;

import java.io.IOException;
import java.util.ArrayList;

public class EntertainmentController extends ChangingPage {
    public EntertainmentController() throws IOException {
        super("https://vnexpress.net/giai-tri", "https://nhandan.vn/thegioi", "https://tuoitre.vn/giai-tri.htm", "https://thanhnien.vn/giai-tri/");
    }
}
